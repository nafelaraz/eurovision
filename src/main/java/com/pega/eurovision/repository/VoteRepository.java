package com.pega.eurovision.repository;

import com.pega.eurovision.model.Vote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote,Long> {

    @Query("select  v.votedFor from Vote as v where v.year=:year group by v.votedFor order by count(v) desc ")
    List<String> getFirstThree(Pageable paging,@Param("year") Integer year);

    @Query("select  v.votedFor from Vote as v where v.year=:year and v.countryFrom=:country group by v.votedFor order by count(v) desc")
    List<String> getFirstThreeOfACountry(Pageable paging,@Param("year") Integer year,@Param("country") String country);

}

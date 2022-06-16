package com.pega.eurovision.service;

import com.pega.eurovision.model.FirstThree;
import com.pega.eurovision.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteRepository voteRepository;


    @Override
    public FirstThree getFirstThree(Integer year) {
        List<String> topThree = voteRepository.getFirstThree(PageRequest.of(0, 3), year);
        if (topThree.size() < 3) {
            return null;
        }

        return new FirstThree(topThree.get(0), topThree.get(1), topThree.get(2));
    }

    @Override
    public FirstThree getFirstThreeOfACountry(Integer year, String country) {
        List<String> topThree = voteRepository.getFirstThreeOfACountry(PageRequest.of(0, 3), year, country);
        if (topThree.size() < 3) {
            return null;
        }

        return new FirstThree(topThree.get(0), topThree.get(1), topThree.get(2));
    }
}

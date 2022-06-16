package com.pega.eurovision.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voteId;

    @Column(name="years")
    private Integer year;
    @NonNull
    private String countryFrom;
    @NonNull
    private String votedFor;

}

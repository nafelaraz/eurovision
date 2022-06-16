package com.pega.eurovision.service;

import com.pega.eurovision.model.FirstThree;


public interface VoteService {
    FirstThree getFirstThree(Integer year);
    FirstThree getFirstThreeOfACountry(Integer year, String country);
}

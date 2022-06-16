package com.pega.eurovision.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pega.eurovision.model.FirstThree;
import com.pega.eurovision.model.Vote;
import com.pega.eurovision.repository.VoteRepository;
import com.pega.eurovision.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping(value = "/votes")
public class VoteController {

    @Autowired
    VoteService voteService;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    private Queue queue;

    @Autowired
    private JmsTemplate jmsTemplate;

    //redis
    @Cacheable
    @GetMapping(value = "{year}")
    public FirstThree getFirstThree(@PathVariable(value = "year") Integer year){
        return voteService.getFirstThree(year);
    }


    //redis
    @Cacheable
    @GetMapping(value = "{year}/{country}")
    public FirstThree getFirstThreeOfACountry(@PathVariable(value = "year") Integer year,@PathVariable(value = "country") String country){
        return voteService.getFirstThreeOfACountry(year,country);
    }

    @GetMapping()
    public List<Vote> getVotes(){
        return voteRepository.findAll();
    }

    @PostMapping(value = "{year}")
    public Vote insertVote(@RequestBody @Valid Vote vote,@PathVariable(value = "year") Integer year) throws JsonProcessingException {
        vote.setYear(year);
        ObjectMapper objectMapper = new ObjectMapper();
        String msg = objectMapper.writeValueAsString(vote);
        jmsTemplate.convertAndSend(queue,msg);
        return vote;
    }
}

package com.pega.eurovision.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pega.eurovision.model.Vote;
import com.pega.eurovision.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    VoteRepository voteRepository;

    @JmsListener(destination = "simple-jms-queue")
    public void listener(String msg) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Vote vote = objectMapper.readValue(msg, Vote.class);
        voteRepository.save(vote);
    }
}
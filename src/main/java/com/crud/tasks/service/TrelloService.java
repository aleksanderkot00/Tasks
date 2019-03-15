package com.crud.tasks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrelloService {

    @Autowired
    private TrelloService trelloService;

    @Autowired
    private SimpleEmailService emailService;
}

package com.example.grpcclient.controller;

import com.example.grpcclient.client.GreetingClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class ClientController {

    @Autowired
    GreetingClient greetingClient;

    private Logger logger = LoggerFactory.getLogger(ClientController.class);

    @RequestMapping("/ping")
    @ResponseBody
    public ResponseEntity returnData()
    {
        return new ResponseEntity("Client is running!", null, HttpStatus.ACCEPTED);
    }

    @RequestMapping("/greeting/{name}")
    @ResponseBody
    public ResponseEntity giveMeAName(@PathVariable("name") String name)
    {
        long startTime = System.currentTimeMillis();
        String response = greetingClient.sendMessage(name);
        logger.info("Time to receive {} in ms: {}", response, (System.currentTimeMillis() - startTime));
        return new ResponseEntity(response, null, HttpStatus.ACCEPTED);
    }
}

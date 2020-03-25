package com.example.grpcclient.client;


import com.example.greeting.GreeterGrpc;
import com.example.greeting.HelloReply;
import com.example.greeting.HelloRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingClient {

    private static Logger logger = LoggerFactory.getLogger(GreetingClient.class);

    @Autowired
    private GreeterGrpc.GreeterBlockingStub clientStub;

    public String sendMessage(final String name){
        final HelloReply response = this.clientStub
                .sayHello(HelloRequest.newBuilder()
                .setName(name)
                .build());

        logger.info("Got response {}", response.getReply());

        return response.getReply();
    }


}

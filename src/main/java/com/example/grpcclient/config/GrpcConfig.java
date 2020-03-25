package com.example.grpcclient.config;

import com.example.greeting.GreeterGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NegotiationType;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.File;

@Configuration
public class GrpcConfig {

    @Value("${grpc.out.custom.certificate}")
    private String certificatePath;

    @Value("${grpc.out.custom.certificate.chain}")
    private String certificateChainPath;

    @Value("${grpc.out.custom.key}")
    private String keyPath;

    @Value("${grpc.out.custom.hostname:localhost}")
    private String hostname;

    @Value("${grpc.out.custom.port:9393}")
    private int port;

    @Bean
    public GreeterGrpc.GreeterBlockingStub clientStub() {
        return GreeterGrpc.newBlockingStub(getChannel());
    }

    public ManagedChannel getChannel()
    {
        if(port == 0){
            return NettyChannelBuilder.forTarget(hostname)
                    .sslContext(getSslContext())
                    .negotiationType(NegotiationType.TLS)
                    .build();
        }
        else
        {
            return NettyChannelBuilder.forAddress(hostname, port)
                    .sslContext(getSslContext())
                    .negotiationType(NegotiationType.TLS)
                    .build();
        }
    }

    public SslContext getSslContext()
    {
        try
        {
            SslContext sslContext = SslContextBuilder.forClient()
                    .trustManager(new File(certificatePath))
                    .keyManager(new File(certificateChainPath), new File(keyPath))
                    .sslProvider(SslProvider.JDK).applicationProtocolConfig(getApplicationProtocolConfig())
                    .build();

            return sslContext;
        }
        catch (SSLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ApplicationProtocolConfig getApplicationProtocolConfig()
    {
        return new ApplicationProtocolConfig(
                ApplicationProtocolConfig.Protocol.ALPN,
                ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL,
                ApplicationProtocolConfig.SelectedListenerFailureBehavior.FATAL_ALERT,
                ApplicationProtocolNames.HTTP_2);
    }
}

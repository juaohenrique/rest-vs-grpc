package br.com.jh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

import br.com.jh.stubs.ConsultaVeiculoGrpc;

@Configuration
public class GrpcClientsConfig {

    @Bean
    ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub ConsultaVeiculoServiceStub(
            GrpcChannelFactory channels
    ) {
        return ConsultaVeiculoGrpc.newBlockingStub(
                channels.createChannel("consultaVeiculoService")
        );
    }
}
package br.com.jh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import multa.v1.ConsultaMultasGrpc;
import multa.v1.MultaOuterClass.MultaRequest;
import multa.v1.MultaOuterClass.MultaResponse;

@Service
public class MultaClient {
	
    private static final Logger log = LoggerFactory.getLogger(MultaClient.class);
    
    private final ConsultaMultasGrpc.ConsultaMultasBlockingStub stub;
    
    public MultaClient() {
    	
    	// Cria o canal manualmente (porta do multa-service)
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9005)
                .usePlaintext()
                .build();

        this.stub = ConsultaMultasGrpc.newBlockingStub(channel);
    }
    
    public MultaResponse listarPorPlaca(String placa) {
//        log.info("VEICULO-SERVICE chamando MULTA-SERVICE via gRPC. Placa={}", placa);

        MultaRequest request = MultaRequest.newBuilder()
                .setPlaca(placa)
                .build();

        MultaResponse response = stub.listarPorPlaca(request);
        return response;
        
    }
    
    


}

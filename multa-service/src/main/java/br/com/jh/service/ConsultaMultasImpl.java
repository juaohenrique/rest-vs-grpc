package br.com.jh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import br.com.jh.stubs.ConsultaMultasGrpc;
import br.com.jh.stubs.Multa;
import br.com.jh.stubs.MultaResponse;
import br.com.jh.stubs.MultaRequest;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ConsultaMultasImpl extends ConsultaMultasGrpc.ConsultaMultasImplBase  {
	
    private static final Logger log = LoggerFactory.getLogger(ConsultaMultasImpl.class);
    
    @Override
    public void listarPorPlaca(MultaRequest req, StreamObserver<MultaResponse> responseObserver) {
    	
    	log.info("Consulta multa por placa: " + req);
    	
    	List<Multa> multas = List.of(
    	        Multa.newBuilder().setId(1).setPlaca(req.getPlaca()).setCtb("Excesso de velocidade").build(),
    	        Multa.newBuilder().setId(2).setPlaca(req.getPlaca()).setCtb("Estacionar em local proibido").build(),
    	        Multa.newBuilder().setId(3).setPlaca(req.getPlaca()).setCtb("Avançar sinal vermelho").build()
    	    );
    	
    	MultaResponse reply = MultaResponse.newBuilder()
    			.addAllListaMultas(multas).build();
    	
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        
    }

}

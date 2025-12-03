package br.com.jh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import br.com.jh.stubs.ConsultaMultasGrpc;
import br.com.jh.stubs.Multa;
import br.com.jh.stubs.MultaOuterClass;
import br.com.jh.stubs.MultaRequest;
import br.com.jh.stubs.MultaResponse;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ConsultaGrpcMultasImpl extends ConsultaMultasGrpc.ConsultaMultasImplBase  {
	
    private static final Logger log = LoggerFactory.getLogger(ConsultaGrpcMultasImpl.class);
    
    @Autowired
    private ConsultaServiceImpl service;
    
    @Override
    public void listarPorPlaca(MultaRequest req, StreamObserver<MultaResponse> responseObserver) {
    	
    	log.info("Consulta multa por placa: " + req);
    	
    	List<br.com.jh.entity.Multa> multasEntity = service.listarPorPlaca(req.getPlaca());
    	
    	List<Multa> multas = new ArrayList<>();
    	
    	multasEntity.forEach(m -> {
    		multas.add(Multa.newBuilder().setId(m.getId()).setPlaca(m.getPlaca()).setCtb(m.getCtb()).build());
    	});
    	    	
    	MultaResponse reply = MultaResponse.newBuilder()
    			.addAllListaMultas(multas).build();
    	
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        
    }

}

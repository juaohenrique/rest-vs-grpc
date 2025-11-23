package br.com.jh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import br.com.jh.stubs.ConsultaVeiculoGrpc;
import br.com.jh.stubs.VeiculoRequest;
import br.com.jh.stubs.VeiculoResponse;
import io.grpc.stub.StreamObserver;
import multa.v1.MultaOuterClass.Multa;
import multa.v1.MultaOuterClass.MultaResponse;

@GrpcService
public class VeiculoServiceImpl extends ConsultaVeiculoGrpc.ConsultaVeiculoImplBase {

	private static final Logger log = LoggerFactory.getLogger(VeiculoServiceImpl.class);

	private final MultaClient multaClient;
	
	public VeiculoServiceImpl(MultaClient multaClient) {
		this.multaClient = multaClient;
	}
	
	@Override
	public void getVeiculo(VeiculoRequest req, StreamObserver<VeiculoResponse> responseObserver) {
		
		String placa = req.getPlaca();
        log.info("Consulta veículo por placa. placa={}", placa);
        
        MultaResponse multaResponse = multaClient.listarPorPlaca(placa);
        
        List<Multa> multas = List.of(
    	        Multa.newBuilder().setId(1).setPlaca(req.getPlaca()).setCtb("Excesso de velocidade").build(),
    	        Multa.newBuilder().setId(2).setPlaca(req.getPlaca()).setCtb("Estacionar em local proibido").build(),
    	        Multa.newBuilder().setId(3).setPlaca(req.getPlaca()).setCtb("Avançar sinal vermelho").build()
    	    );
        
        System.out.println("LISTA DE MULTAS: " + multaResponse.getListaMultasList());
        
        
        VeiculoResponse veiculoResponse = VeiculoResponse.newBuilder()
    		.setId(1)
    		.setPlaca(placa)
    		.setAno(placa)
    		.setMarca(placa)
    		.setModelo(placa)
    		.setCor(placa)
    		.addAllListaMultas(multaResponse.getListaMultasList())
    		.build();
        
        log.info("Enviando resposta com {} multas", veiculoResponse.getListaMultasCount());
        
        responseObserver.onNext(veiculoResponse);
        responseObserver.onCompleted();
		
	}

	
}

package br.com.jh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.jh.stubs.ConsultaVeiculoGrpc;
import br.com.jh.stubs.VeiculoRequest;
import br.com.jh.stubs.VeiculoResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import multa.v1.ConsultaMultasGrpc;
import multa.v1.MultaOuterClass.MultaRequest;
import multa.v1.MultaOuterClass.MultaResponse;

//@Service
//public class VeiculoClient {
//
//	private static final Logger log = LoggerFactory.getLogger(VeiculoClient.class);
//
//	private final ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub stub;
//
//	public VeiculoClient() {
//		// Cria o canal manualmente (porta do veiculo-service)
//		ManagedChannel channel = ManagedChannelBuilder
//				.forAddress("localhost", 9004)
//				.usePlaintext()
//				.build();
//
//		this.stub = ConsultaVeiculoGrpc.newBlockingStub(channel);
//	}
//
//	public VeiculoResponse getVeiculo(String placa) {
//		
//		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC. Placa={}", placa);
//
//        VeiculoRequest request = VeiculoRequest.newBuilder()
//                .setPlaca(placa)
//                .build();
//
//        VeiculoResponse response = stub.getVeiculo(request);
//        return response;
//		
//	}
//}

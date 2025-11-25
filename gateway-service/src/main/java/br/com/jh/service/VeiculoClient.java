package br.com.jh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.jh.stubs.veiculo.ConsultaVeiculoGrpc;
import br.com.jh.stubs.veiculo.Empty;
import br.com.jh.stubs.veiculo.ListaVeiculoResponse;
import br.com.jh.stubs.veiculo.VeiculoMultaResponse;
import br.com.jh.stubs.veiculo.VeiculoRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class VeiculoClient {

	private static final Logger log = LoggerFactory.getLogger(VeiculoClient.class);

	private final ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub stub;

	public VeiculoClient() {
		// Cria o canal manualmente (porta do veiculo-service)
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 9004)
				.usePlaintext()
				.build();

		this.stub = ConsultaVeiculoGrpc.newBlockingStub(channel);
	}

	public VeiculoMultaResponse findByPlaca(String placa) {
		
		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC. Placa={}", placa);

        VeiculoRequest request = VeiculoRequest.newBuilder()
                .setPlaca(placa)
                .build();

        VeiculoMultaResponse response = stub.findByPlaca(request);
        return response;
		
	}

	public ListaVeiculoResponse findAll() {
		
		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC.");
		
		ListaVeiculoResponse response = stub.findAll(Empty.getDefaultInstance());
		
		return response;
	}
}

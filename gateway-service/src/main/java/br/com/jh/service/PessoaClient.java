package br.com.jh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.jh.stubs.ConsultaPessoaGrpc;
import br.com.jh.stubs.Empty;
import br.com.jh.stubs.ListaPessoaResponse;
import br.com.jh.stubs.PessoaRequest;
import br.com.jh.stubs.PessoaResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class PessoaClient {

	private static final Logger log = LoggerFactory.getLogger(PessoaClient.class);
	
	private final ConsultaPessoaGrpc.ConsultaPessoaBlockingStub stub;

	public PessoaClient() {
		// Cria o canal manualmente (porta do pessoa-service)
		ManagedChannel channel = ManagedChannelBuilder
				.forAddress("localhost", 9002)
				.usePlaintext()
				.build();

		this.stub = ConsultaPessoaGrpc.newBlockingStub(channel);
	}

	public PessoaResponse findByCpf(String cpf) {
		
		log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC. CPF={}", cpf);

        PessoaRequest request = PessoaRequest.newBuilder()
                .setCpf(cpf)
                .build();

        PessoaResponse response = stub.findByCpf(request);
        return response;
		
	}
	
	public ListaPessoaResponse findByNome(String nome) {
	
		log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
		
		PessoaRequest request = PessoaRequest.newBuilder()
                .setNome(nome)
                .build();
		
		ListaPessoaResponse response = stub.findByNome(request);
		
        return response;
	}

	public ListaPessoaResponse findAll() {
		
		log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
		
		ListaPessoaResponse response = stub.findAll(Empty.getDefaultInstance());
		
        return response;
	}
}

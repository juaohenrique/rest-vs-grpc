package br.com.jh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.jh.stubs.ConsultaPessoaGrpc;
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

	public PessoaResponse getPessoa(String cpf) {
		
		log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC. CPF={}", cpf);

        PessoaRequest request = PessoaRequest.newBuilder()
                .setCpf(cpf)
                .build();

        PessoaResponse response = stub.getPessoa(request);
        return response;
		
	}
}

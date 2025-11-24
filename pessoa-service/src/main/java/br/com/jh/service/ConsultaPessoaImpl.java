package br.com.jh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import br.com.jh.stubs.ConsultaPessoaGrpc;
import br.com.jh.stubs.PessoaRequest;
import br.com.jh.stubs.PessoaResponse;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ConsultaPessoaImpl extends ConsultaPessoaGrpc.ConsultaPessoaImplBase {

    private static final Logger log = LoggerFactory.getLogger(ConsultaPessoaImpl.class);
    
    @Override
    public void getPessoa(PessoaRequest req, StreamObserver<PessoaResponse> responseObserver) {
    
    	log.info("Consulta pessoa por cpf: " + req);
    	
    	PessoaResponse response = PessoaResponse.newBuilder()
			.setId(1)
			.setNome("joao henrique silva")
			.setNascimento("08/08/1888")
			.setFone("(98) 9876-1234")
			.setEndereco("Av. 1")
			.build();
    	
        responseObserver.onNext(response);
        responseObserver.onCompleted();    	    
    }

	
}

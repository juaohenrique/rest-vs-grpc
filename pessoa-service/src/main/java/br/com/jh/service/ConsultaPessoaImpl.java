package br.com.jh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import br.com.jh.stubs.ConsultaPessoaGrpc;
import br.com.jh.stubs.Empty;
import br.com.jh.stubs.ListaPessoaResponse;
import br.com.jh.stubs.PessoaRequest;
import br.com.jh.stubs.PessoaResponse;
import io.grpc.stub.StreamObserver;

@GrpcService
public class ConsultaPessoaImpl extends ConsultaPessoaGrpc.ConsultaPessoaImplBase {

    private static final Logger log = LoggerFactory.getLogger(ConsultaPessoaImpl.class);
    
    @Override
    public void findByCpf(PessoaRequest req, StreamObserver<PessoaResponse> responseObserver) {
    
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
    
    
    @Override
    public void findByNome(PessoaRequest req, StreamObserver<ListaPessoaResponse> responseObserver) {
    
    	log.info("Consulta pessoa por nome: " + req);
    	
    	List<PessoaResponse> listaPessoas = List.of(
   			 PessoaResponse.newBuilder().setId(1).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(2).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(3).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(4).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(5).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(6).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(7).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(8).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(9).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(10).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(11).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(12).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(13).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(14).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(15).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(16).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(17).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(18).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(19).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
   			 PessoaResponse.newBuilder().setId(20).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build()
   	 );
    	
    
    	// filtrar por parte do nome
    	List<PessoaResponse> filtradosContains = listaPessoas
    		.stream()
    	    .filter(p -> p.getNome().toLowerCase().contains(req.getNome()))
    	    .collect(Collectors.toList());

    	
   	 ListaPessoaResponse listaPessoaResponse = ListaPessoaResponse.newBuilder().addAllListaPessoas(filtradosContains).build();
    	
        responseObserver.onNext(listaPessoaResponse);
        responseObserver.onCompleted();    	    
    }
    
    
    @Override
    public void findAll(Empty req, StreamObserver<ListaPessoaResponse> responseObserver) {
    	
    	 log.info("Listando todas as pessoas...");
    	 
    	 
    	 List<PessoaResponse> listaPessoas = List.of(
    			 PessoaResponse.newBuilder().setId(1).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(2).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(3).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(4).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(5).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(6).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(7).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(8).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(9).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(10).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(11).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(12).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(13).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(14).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(15).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(16).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(17).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(18).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(19).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build(),
    			 PessoaResponse.newBuilder().setId(20).setNome("joao henrique silva").setNascimento("08/08/1888").setFone("(98) 9876-1234").setEndereco("Av. 1").build()
    	 );
    	 
    	 ListaPessoaResponse listaPessoaResponse = ListaPessoaResponse.newBuilder().addAllListaPessoas(listaPessoas).build();
    	 
    	 responseObserver.onNext(listaPessoaResponse);
         responseObserver.onCompleted();    
    }

	
}

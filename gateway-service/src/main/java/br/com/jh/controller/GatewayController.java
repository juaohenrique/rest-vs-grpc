package br.com.jh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jh.dto.MultaDTO;
import br.com.jh.dto.PessoaDTO;
import br.com.jh.dto.VeiculoDTO;
import br.com.jh.service.PessoaClient;
import br.com.jh.service.VeiculoClient;
import br.com.jh.stubs.ListaPessoaResponse;
import br.com.jh.stubs.PessoaResponse;
import br.com.jh.stubs.veiculo.ListaVeiculoResponse;
import br.com.jh.stubs.veiculo.VeiculoMultaResponse;

@RestController
@RequestMapping(value = "/consulta")
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);
    
//	private final ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub veiculoStub;
//	private final ConsultaPessoaGrpc.ConsultaPessoaBlockingStub pessoaStub;
//	
//	public GatewayController(
//			ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub veiculoStub,
//			ConsultaPessoaGrpc.ConsultaPessoaBlockingStub pessoaStub) {
//		this.veiculoStub = veiculoStub;
//		this.pessoaStub = pessoaStub;
//	}
    
    private final VeiculoClient veiculoClient;
    private final PessoaClient pessoaClient;
    
    public GatewayController(VeiculoClient veiculoClient, PessoaClient pessoaClient) {
    	this.veiculoClient = veiculoClient;
    	this.pessoaClient = pessoaClient;
    }
    
    
    @GetMapping("/pessoas/all") 
    public ResponseEntity<List<PessoaDTO>> getAllPessoas() {
    	
    	log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
    	
    	ListaPessoaResponse listaPessoas = pessoaClient.findAll();
    	
    	List<PessoaDTO> pessoas =  listaPessoas
        	.getListaPessoasList()
        	.stream()
        	.map(p -> new PessoaDTO(p.getId(), p.getNome(), p.getNascimento(), p.getFone(), p.getEndereco(), p.getCpf()))
        	.toList();
    	
    	return ResponseEntity.ok(pessoas);
    }
    
    @GetMapping("/pessoas") 
    public ResponseEntity<List<PessoaDTO>> getByNome(@RequestParam String nome) {
    	
    	log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
    	
    	ListaPessoaResponse listaPessoas = pessoaClient.findByNome(nome);
    	
    	List<PessoaDTO> pessoas =  listaPessoas
        	.getListaPessoasList()
        	.stream()
        	.map(p -> new PessoaDTO(p.getId(), p.getNome(), p.getNascimento(), p.getFone(), p.getEndereco(), p.getCpf()))
        	.toList();
    	
    	return ResponseEntity.ok(pessoas);
    }
    
    
    @GetMapping("/veiculos/all") 
    public ResponseEntity<List<VeiculoDTO>> getAllVeiculos() {
    	
    	log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC.");
    	
    	ListaVeiculoResponse listaVeiculos = veiculoClient.findAll(); 
    	
    	List<VeiculoDTO> veiculos =  listaVeiculos
        	.getListaVeiculosList()
        	.stream()
        	.map(v -> new VeiculoDTO(v.getId(), v.getPlaca(), v.getAno(), v.getMarca(), v.getModelo(), v.getCor(), null, null))
        	.toList();
    	
    	return ResponseEntity.ok(veiculos);
    }
    
	
	@GetMapping("/veiculos")
	public ResponseEntity<VeiculoDTO> buscaVeiculoPorPlaca(@RequestParam String placa) {
		
		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC. Placa={}", placa);
		
//		VeiculoRequest veiculoRequest = VeiculoRequest.newBuilder()
//            .setPlaca(placa)
//            .build();
//
//        VeiculoResponse veiculoResponse = veiculoStub.getVeiculo(veiculoRequest);
		
		VeiculoMultaResponse veiculoResponse = veiculoClient.findByPlaca(placa); 
        
//        PessoaRequest pessoaRequest = PessoaRequest.newBuilder()
//    		.setCpf("111.222.333-44")
//    		.build();
        
//        PessoaResponse pessoaResponse = pessoaStub.getPessoa(pessoaRequest);
        PessoaResponse pessoaResponse = pessoaClient.findByCpf("111.222.333-44");
        
        PessoaDTO pessoaDto = new PessoaDTO(
        	pessoaResponse.getId(), 
        	pessoaResponse.getNome(), 
        	pessoaResponse.getNascimento(), 
        	pessoaResponse.getFone(), 
        	pessoaResponse.getEndereco(), 
        	pessoaResponse.getCpf()
    	);
        
        
        // Converte Protobuf → DTO
        List<MultaDTO> multas = veiculoResponse.getListaMultasList().stream()
            .map(m -> new MultaDTO(m.getId(), m.getPlaca(), m.getCtb()))
            .toList();
        
        VeiculoDTO veiculoDto = new VeiculoDTO(
    		veiculoResponse.getId(),
    		veiculoResponse.getPlaca(),
    		veiculoResponse.getAno(),
    		veiculoResponse.getMarca(),
    		veiculoResponse.getModelo(),
    		veiculoResponse.getCor(),
            multas,
            pessoaDto
        );
        
        return ResponseEntity.ok().body(veiculoDto);
		
	}
	


	
}

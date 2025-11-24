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
import br.com.jh.stubs.PessoaRequest;
import br.com.jh.stubs.PessoaResponse;
import br.com.jh.stubs.VeiculoResponse;

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
	
	@GetMapping("/veiculo")
	public ResponseEntity<VeiculoDTO> buscaVeiculoPorPlaca(@RequestParam String placa) {
		
		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC. Placa={}", placa);
		
//		VeiculoRequest veiculoRequest = VeiculoRequest.newBuilder()
//            .setPlaca(placa)
//            .build();
//
//        VeiculoResponse veiculoResponse = veiculoStub.getVeiculo(veiculoRequest);
		
		VeiculoResponse veiculoResponse = veiculoClient.getVeiculo(placa); 
        
//        PessoaRequest pessoaRequest = PessoaRequest.newBuilder()
//    		.setCpf("111.222.333-44")
//    		.build();
        
//        PessoaResponse pessoaResponse = pessoaStub.getPessoa(pessoaRequest);
        PessoaResponse pessoaResponse = pessoaClient.getPessoa("111.222.333-44");
        
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

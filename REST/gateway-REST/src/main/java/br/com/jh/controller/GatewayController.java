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
import br.com.jh.service.MultaServiceImpl;
import br.com.jh.service.PessoaServiceImpl;
import br.com.jh.service.VeiculoServiceImpl;

@RestController
@RequestMapping(value = "/consulta")
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);
    
    private final PessoaServiceImpl pessoaService;
    private final VeiculoServiceImpl veiculoService;
//    private final MultaServiceImpl multaService;
    
    private GatewayController(
    		 PessoaServiceImpl pessoaService,
    		 VeiculoServiceImpl veiculoService
//    		 MultaServiceImpl multaService
    		 ) {
    	
    	this.pessoaService = pessoaService;
    	this.veiculoService = veiculoService;
//    	this.multaService = multaService;
    }
    
    @GetMapping("/pessoas/por-cpf") 
    public ResponseEntity<PessoaDTO> getByCpf(@RequestParam String cpf) {
    	
    	log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
    	
    	PessoaDTO pessoas = pessoaService.findByCpf(cpf);
    	
    	return ResponseEntity.ok(pessoas);
    }
    
    @GetMapping("/pessoas/por-nome") 
    public ResponseEntity<List<PessoaDTO>> getByNome(@RequestParam String nome) {
    	
    	log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
    	
    	List<PessoaDTO> pessoas = pessoaService.findByNome(nome);
    	
    	return ResponseEntity.ok(pessoas);
    }
    
    @GetMapping("/pessoas/all") 
    public ResponseEntity<List<PessoaDTO>> getAllPessoas() {
    	
    	log.info("GATEWAY-SERVICE chamando PESSOA-SERVICE via gRPC.");
    	
    	List<PessoaDTO> pessoas = pessoaService.findAll();
    	
    	
    	return ResponseEntity.ok(pessoas);
    }
        
	@GetMapping("/veiculos/por-placa")
	public ResponseEntity<VeiculoDTO> buscaVeiculoPorPlaca(@RequestParam String placa) {
		
		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC. Placa={}", placa);
		
		VeiculoDTO veiculo = veiculoService.findByPlaca(placa); 
        
//		PessoaDTO pessoa = pessoaService.findByCpf(veiculo.pessoa().cpf());
		
		
//		List<MultaDTO> multas = List.of(
//    	        new MultaDTO(1, placa, "Excesso de velocidade"),
//    	        new MultaDTO(1, placa, "Estacionar em local proibido"),
//    	        new MultaDTO(1, placa, "Avançar sinal vermelho"),
//    	        new MultaDTO(1, placa, "Ultrapassar em local proibido"),
//    	        new MultaDTO(1, placa, "Dirigir alcoolizado"),
//    	        new MultaDTO(1, placa, "Não respeitar faixa de pedestres"),
//    	        new MultaDTO(1, placa, "Dirigir sem habilitação")
//    	    );
        
//		VeiculoDTO veiculoResponse = new VeiculoDTO(
//				veiculo.id(), 
//				veiculo.placa(), 
//				veiculo.ano(), 
//				veiculo.marca(),
//				veiculo.modelo(),
//				veiculo.cor(),
//				multas,
//				pessoa);
        
        return ResponseEntity.ok(veiculo);
		
		
	}
	
    
    
    @GetMapping("/veiculos/all") 
    public ResponseEntity<List<VeiculoDTO>> getAllVeiculos() {
    	
    	log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC.");
    	
    	List<VeiculoDTO> veiculos = veiculoService.findAll(); 
    	
    	return ResponseEntity.ok(veiculos);
    }
    
	



	
}

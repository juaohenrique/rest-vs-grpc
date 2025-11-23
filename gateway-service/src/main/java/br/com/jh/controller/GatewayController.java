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
import br.com.jh.dto.VeiculoDTO;
import br.com.jh.stubs.ConsultaVeiculoGrpc;
import br.com.jh.stubs.VeiculoRequest;
import br.com.jh.stubs.VeiculoResponse;

@RestController
@RequestMapping(value = "/consulta")
public class GatewayController {

    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);
    
	private final ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub stub;
	
	public GatewayController(ConsultaVeiculoGrpc.ConsultaVeiculoBlockingStub stub) {
		this.stub = stub;
	}
	
	@GetMapping("/veiculo")
	public ResponseEntity<VeiculoDTO> buscaVeiculoPorPlaca(@RequestParam String placa) {
		
		log.info("GATEWAY-SERVICE chamando VEICULO-SERVICE via gRPC. Placa={}", placa);
		
		VeiculoRequest request = VeiculoRequest.newBuilder()
            .setPlaca(placa)
            .build();

        VeiculoResponse response = stub.getVeiculo(request);
        
     // Converte Protobuf → DTO
        List<MultaDTO> multas = response.getListaMultasList().stream()
            .map(m -> new MultaDTO(m.getId(), m.getPlaca(), m.getCtb()))
            .toList();
        
        VeiculoDTO dto = new VeiculoDTO(
        		response.getId(),
        		response.getPlaca(),
        		response.getAno(),
        		response.getMarca(),
        		response.getModelo(),
        		response.getCor(),
                multas
            );
        
        return ResponseEntity.ok().body(dto);
		
	}
	


	
}

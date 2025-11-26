package br.com.jh.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jh.dto.VeiculoDTO;
import br.com.jh.service.VeiculoServiceImpl;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	private final VeiculoServiceImpl service;
	
	private VeiculoController(VeiculoServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping("/por-placa")
	public ResponseEntity<VeiculoDTO> findByPlaca(@RequestParam String placa) {
		
		VeiculoDTO veiculo = service.findByPlaca(placa);
		
		return ResponseEntity.ok(veiculo);
	}
	
	@GetMapping("/por-proprietario")
	public ResponseEntity<List<VeiculoDTO>>  findByCpfProprietario(@RequestParam String cpfProprietario) {
		
		List<VeiculoDTO> veiculos = service.findByCpfProprietario();
		
		return ResponseEntity.ok(veiculos);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<VeiculoDTO>> findAll() {
		
		List<VeiculoDTO> veiculos = service.findAll();
		
		return ResponseEntity.ok(veiculos);
	}

	
}

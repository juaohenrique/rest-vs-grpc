package br.com.jh.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jh.dto.MultaDTO;
import br.com.jh.service.MultaServiceImpl;

@RestController
@RequestMapping("/multas")
public class MultaController {
	
	private final MultaServiceImpl service;
	
	private MultaController(MultaServiceImpl service) {
		this.service = service;
	}
	
	@GetMapping("/por-placa")
	public ResponseEntity<List<MultaDTO>> findByPlaca(@RequestParam String placa) {
		
		List<MultaDTO> multas = service.findByPlaca(placa);
		
		return ResponseEntity.ok(multas);
	}

}

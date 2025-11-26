package br.com.jh.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.jh.dto.PessoaDTO;
import br.com.jh.service.PessoaServiceImpl;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	private final PessoaServiceImpl service;
	private PessoaController(PessoaServiceImpl service) {
		this.service = service;
	}

	@GetMapping("/por-cpf")
	public ResponseEntity<PessoaDTO> findByCpf(@RequestParam String cpf) {
		
		PessoaDTO pessoa = service.findByCpf(cpf);
		
		return ResponseEntity.ok(pessoa);
	}
	
	@GetMapping("/por-nome")
	public ResponseEntity<List<PessoaDTO>> findByNome(@RequestParam String nome) {
		
		List<PessoaDTO> pessoas = service.findByNome(nome);
		
		return ResponseEntity.ok(pessoas);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PessoaDTO>> findAll() {
		
		List<PessoaDTO> pessoas = service.findAll();
		
		return ResponseEntity.ok(pessoas);
	}
	
}

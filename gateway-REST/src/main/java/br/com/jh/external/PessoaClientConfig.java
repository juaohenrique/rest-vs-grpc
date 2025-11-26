package br.com.jh.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.jh.dto.PessoaDTO;

@FeignClient(name = "pessoa-REST", url = "http://localhost:8002")
public interface PessoaClientConfig {
	
	@GetMapping("/pessoas/por-cpf")
	PessoaDTO findByCpf(@RequestParam String cpf);

    @GetMapping("/pessoas/por-nome")
    List<PessoaDTO> findByNome(@RequestParam String nome);
    
    @GetMapping("/pessoas/all")
    List<PessoaDTO> findAll();

}

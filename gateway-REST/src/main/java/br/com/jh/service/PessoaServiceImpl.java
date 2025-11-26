package br.com.jh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.jh.dto.PessoaDTO;
import br.com.jh.external.PessoaClientConfig;

@Service
public class PessoaServiceImpl {

	
	private final PessoaClientConfig pessoaClient;
	private PessoaServiceImpl(PessoaClientConfig pessoaClient) {
		this.pessoaClient = pessoaClient; 
	}

	public PessoaDTO findByCpf(String cpf) {
		
		PessoaDTO pessoa = pessoaClient.findByCpf(cpf);
				
		return pessoa;
	}

	public List<PessoaDTO> findByNome(String nome) {
		
		List<PessoaDTO> pessoas = pessoaClient.findByNome(nome);
		
		return pessoas;
		
	}

	public List<PessoaDTO> findAll() {
		
		List<PessoaDTO> pessoas = pessoaClient.findAll();
		return pessoas;
	}
}

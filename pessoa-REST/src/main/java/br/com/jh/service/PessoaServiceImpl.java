package br.com.jh.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jh.dao.PessoaDAO;
import br.com.jh.dto.PessoaDTO;
import br.com.jh.entity.Pessoa;

@Service
public class PessoaServiceImpl {
	
	@Autowired
	private PessoaDAO dao;

	public PessoaDTO findByCpf(String cpf) {

		Pessoa pessoa = dao.findByCpf(cpf);
		
		return new PessoaDTO(
				pessoa.getId(), 
				pessoa.getNome(), 
				pessoa.getNascimento(), 
				pessoa.getFone(), 
				pessoa.getEndereco(), 
				pessoa.getCpf());
	}

	public List<PessoaDTO> findByNome(String nome) {
		
		List<PessoaDTO> pessoas = List.of(
				 new PessoaDTO(1, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(2, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(3, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(4, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(5, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(6, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(7, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(8, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(9, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(10, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(11, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(12, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(13, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(14, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(15, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(16, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(17, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(18, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(19, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),	
				 new PessoaDTO(20, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44")
		);
		
		List<PessoaDTO> filtradosContains = pessoas
	    		.stream()
	    	    .filter(p -> p.nome().toLowerCase().contains(nome))
	    	    .collect(Collectors.toList());
	
		return filtradosContains;
		
	}

	public List<PessoaDTO> findAll() {
		
		List<PessoaDTO> pessoas = List.of(
				 new PessoaDTO(1, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(2, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(3, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(4, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(5, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(6, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(7, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(8, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(9, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(10, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(11, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(12, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(13, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(14, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(15, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(16, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(17, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(18, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),
				 new PessoaDTO(19, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44"),	
				 new PessoaDTO(20, "joao henrique silva", "08/08/1888", "(98) 9876-1234", "Av. 1", "111.222.333-44")
		);
		
		return pessoas;
	}

}

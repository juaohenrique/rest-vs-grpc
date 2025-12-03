package br.com.jh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jh.dao.PessoaDAO;
import br.com.jh.entity.Pessoa;

@Service
public class PessoaServiceImpl {
	
	@Autowired
	private PessoaDAO dao;
	
	public  Pessoa findByCpf(String cpf) {
		return dao.findByCpf(cpf);
	}

}

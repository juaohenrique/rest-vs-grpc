package br.com.jh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jh.dao.VeiculoDAO;
import br.com.jh.entity.Veiculo;

@Service
public class VeiculoServiceImpl {
	
	@Autowired
	private VeiculoDAO dao;

	public Veiculo findByPlaca(String placa) {
		
		return dao.findByPlaca(placa);
		
	}
	
}

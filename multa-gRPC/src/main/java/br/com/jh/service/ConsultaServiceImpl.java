package br.com.jh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jh.dao.MultaDAO;
import br.com.jh.entity.Multa;

@Service
public class ConsultaServiceImpl {
	
	@Autowired
	private MultaDAO dao;
	
	public List<Multa> listarPorPlaca(String placa) {
		
		List<Multa> multas =  dao.findByPlaca(placa);
		
		return multas;
		
	}

}

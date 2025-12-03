package br.com.jh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jh.dao.MultaDAO;
import br.com.jh.dto.MultaDTO;

@Service
public class MultaServiceImpl {
	
	@Autowired
	private MultaDAO dao;

	public List<MultaDTO> findByPlaca(String placa) {
		
		List<MultaDTO> multas = new ArrayList<>();
		
		dao.findByPlaca(placa).forEach(m -> {
			multas.add(new MultaDTO(m.getId(), m.getPlaca(), m.getCtb()));
		});
		
		return multas;
	}

}

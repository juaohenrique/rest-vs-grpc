package br.com.jh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.jh.dto.MultaDTO;

@Service
public class MultaServiceImpl {
	
	// repository

	public List<MultaDTO> findByPlaca(String placa) {
		
		List<MultaDTO> multas = List.of(
    	        new MultaDTO(1, placa, "Excesso de velocidade"),
    	        new MultaDTO(1, placa, "Estacionar em local proibido"),
    	        new MultaDTO(1, placa, "Avançar sinal vermelho"),
    	        new MultaDTO(1, placa, "Ultrapassar em local proibido"),
    	        new MultaDTO(1, placa, "Dirigir alcoolizado"),
    	        new MultaDTO(1, placa, "Não respeitar faixa de pedestres"),
    	        new MultaDTO(1, placa, "Dirigir sem habilitação")
    	    );
		return multas;
	}

}

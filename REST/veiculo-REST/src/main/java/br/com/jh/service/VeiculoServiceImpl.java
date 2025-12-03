package br.com.jh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jh.dao.VeiculoDAO;
import br.com.jh.dto.MultaDTO;
import br.com.jh.dto.VeiculoDTO;
import br.com.jh.entity.Veiculo;
import br.com.jh.external.MultaClientConfig;

@Service
public class VeiculoServiceImpl {
	
	@Autowired
	private VeiculoDAO dao;
	
	private final MultaClientConfig multaClient;

	private VeiculoServiceImpl(VeiculoDAO dao, MultaClientConfig multaClient) {
		this.multaClient = multaClient;
		this.dao = dao;
	}

	public List<VeiculoDTO> findAll() {
		
		List<VeiculoDTO> listaVeiculo = List.of(
				new VeiculoDTO(1, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco","111.222.333-44", null, null),
				new VeiculoDTO(2, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(3, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(4, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(5, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(6, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null));
	    		
		return listaVeiculo;
	}

	public List<VeiculoDTO> findByCpfProprietario() {
		List<VeiculoDTO> listaVeiculo = List.of(
				new VeiculoDTO(1, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(2, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(3, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(4, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(5, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null),
				new VeiculoDTO(6, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null));
	    		
		return listaVeiculo;
	}

	public VeiculoDTO findByPlaca(String placa) {

		Veiculo veiculo = dao.findByPlaca(placa);
		
		List<MultaDTO> multas = multaClient.findByPlaca(placa);
		
		VeiculoDTO veiculoCompleto = new VeiculoDTO(
				veiculo.getId(),
				veiculo.getPlaca(),
				veiculo.getAno(),
				veiculo.getMarca(),
				veiculo.getModelo(),
				veiculo.getCor(),
				veiculo.getCpfProprietario(),
				multas,
				null
				);
		
		return veiculoCompleto;
	}

}

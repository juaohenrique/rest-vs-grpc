package br.com.jh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.jh.dto.MultaDTO;
import br.com.jh.dto.VeiculoDTO;
import br.com.jh.external.MultaClientConfig;

@Service
public class VeiculoServiceImpl {
	
	private final MultaClientConfig multaClient;

	private VeiculoServiceImpl(MultaClientConfig multaClient) {
		this.multaClient = multaClient;
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
//		
		VeiculoDTO veiculo = new VeiculoDTO(1, "ptl-3564", "2018", "Volkswagem", "Gol 1.0", "branco", "111.222.333-44", null, null);
		
		List<MultaDTO> multas = multaClient.findByPlaca(placa);
		
		VeiculoDTO veiculoCompleto = new VeiculoDTO(
				veiculo.id(),
				veiculo.placa(),
				veiculo.ano(),
				veiculo.marca(),
				veiculo.modelo(),
				veiculo.cor(),
				veiculo.cpfProprietario(),
				multas,
				null
				);
		
		return veiculoCompleto;
	}

}

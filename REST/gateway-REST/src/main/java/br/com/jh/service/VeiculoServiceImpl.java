package br.com.jh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.jh.dto.PessoaDTO;
import br.com.jh.dto.VeiculoDTO;
import br.com.jh.external.PessoaClientConfig;
import br.com.jh.external.VeiculoClientConfig;

@Service
public class VeiculoServiceImpl {
	
	private final VeiculoClientConfig veiculoClient;
	private final PessoaClientConfig pessoaClient;
	
	private VeiculoServiceImpl(VeiculoClientConfig veiculoClient, PessoaClientConfig pessoaClient) {
		this.veiculoClient = veiculoClient; 
		this.pessoaClient = pessoaClient; 
	}
	
	public List<VeiculoDTO> findAll() {
		
		List<VeiculoDTO> listaVeiculo = veiculoClient.findAll();
	    		
		return listaVeiculo;
	}

	public VeiculoDTO findByPlaca(String placa) {
		
		VeiculoDTO veiculo = veiculoClient.findByPlaca(placa);
		
		PessoaDTO pessoa = pessoaClient.findByCpf(veiculo.cpfProprietario());
		
		VeiculoDTO veiculoCompleto = new VeiculoDTO(
				veiculo.id(),
				veiculo.placa(),
				veiculo.ano(),
				veiculo.marca(),
				veiculo.modelo(),
				veiculo.cor(),
				veiculo.cpfProprietario(),
				veiculo.multas(),
				pessoa
		);
		
		return veiculoCompleto;
	}

}

package br.com.jh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.jh.dto.MultaDTO;
import br.com.jh.dto.PessoaDTO;
import br.com.jh.dto.VeiculoDTO;
import br.com.jh.external.PessoaClient;
import br.com.jh.external.VeiculoClient;
import br.com.jh.stubs.PessoaResponse;
import br.com.jh.stubs.veiculo.VeiculoMultaResponse;

@Service
public class VeiculoServiceImpl {
	
	private final VeiculoClient veiculoClient;
	private final PessoaClient pessoaClient;
	
	private VeiculoServiceImpl(
			VeiculoClient veiculoClient,
			PessoaClient pessoaClient) {
		this.veiculoClient = veiculoClient;
		this.pessoaClient = pessoaClient;
	}
	
//	public List<VeiculoDTO> findAll() {
//		
//		List<VeiculoDTO> listaVeiculo = veiculoClient.findAll();
//	    		
//		return listaVeiculo;
//	}

	public VeiculoDTO findByPlaca(String placa) {
		
		VeiculoMultaResponse veiculo = veiculoClient.findByPlaca(placa);
		
		PessoaResponse pessoa = pessoaClient.findByCpf(veiculo.getCpfProprietario());
		
		List<MultaDTO> multas = veiculo.getListaMultasList().stream()
	            .map(m -> new MultaDTO(m.getId(), m.getPlaca(), m.getCtb()))
	            .toList();
		
		VeiculoDTO veiculoCompleto = new VeiculoDTO(
				veiculo.getId(), 
				veiculo.getPlaca(), 
				veiculo.getAno(), 
				veiculo.getMarca(), 
				veiculo.getModelo(), 
				veiculo.getCor(), 
				veiculo.getCpfProprietario(), 
				multas,
				new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getNascimento(), pessoa.getFone(), pessoa.getEndereco(), pessoa.getCpf()));
		
		return veiculoCompleto;
	}

}

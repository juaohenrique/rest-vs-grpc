package br.com.jh.dto;

public record PessoaDTO(
	int id,
	String nome,
	String nascimento, 
	String fone,
	String endereco,
	String cpf
){}

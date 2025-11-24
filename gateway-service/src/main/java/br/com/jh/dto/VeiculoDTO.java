package br.com.jh.dto;

import java.util.List;

public record VeiculoDTO (
    int id,
    String placa,
    String ano,
    String marca,
    String modelo,
    String cor,
    List<MultaDTO> multas,
    PessoaDTO pessoa
) {}
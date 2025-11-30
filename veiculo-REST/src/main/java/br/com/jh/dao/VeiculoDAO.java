package br.com.jh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jh.entity.Veiculo;

@Repository
public interface VeiculoDAO extends JpaRepository<Veiculo, Integer> {

	Veiculo findByPlaca(String placa);

}

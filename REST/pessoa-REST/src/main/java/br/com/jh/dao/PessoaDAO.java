package br.com.jh.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jh.entity.Pessoa;

@Repository
public interface PessoaDAO extends JpaRepository<Pessoa, Integer> {

	Pessoa findByCpf(String cpf);

}

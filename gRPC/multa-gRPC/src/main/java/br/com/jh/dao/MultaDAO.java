package br.com.jh.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jh.entity.Multa;

@Repository
public interface MultaDAO extends JpaRepository<Multa, Integer>{

	List<Multa> findByPlaca(String placa);

}

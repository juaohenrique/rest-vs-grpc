package br.com.jh.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.jh.dto.VeiculoDTO;

@FeignClient(name = "veiculo-REST", url = "http://localhost:8004")
public interface VeiculoClientConfig {
	
	@GetMapping("/veiculos/all")
    List<VeiculoDTO> findAll();

    @GetMapping("/veiculos/por-placa")
    VeiculoDTO findByPlaca(@RequestParam String placa);

}

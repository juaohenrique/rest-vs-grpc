package br.com.jh.external;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.jh.dto.MultaDTO;

@FeignClient(name = "multa-REST", url = "http://localhost:8005")
public interface MultaClientConfig {

	@GetMapping("/multas/por-placa")
	List<MultaDTO> findByPlaca(@RequestParam String placa);
	
}

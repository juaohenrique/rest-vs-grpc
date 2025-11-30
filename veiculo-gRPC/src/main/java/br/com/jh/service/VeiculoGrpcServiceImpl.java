package br.com.jh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.grpc.server.service.GrpcService;

import br.com.jh.entity.Veiculo;
import br.com.jh.stubs.veiculo.ConsultaVeiculoGrpc;
import br.com.jh.stubs.veiculo.Empty;
import br.com.jh.stubs.veiculo.ListaVeiculoResponse;
import br.com.jh.stubs.veiculo.VeiculoMultaResponse;
import br.com.jh.stubs.veiculo.VeiculoRequest;
import br.com.jh.stubs.veiculo.VeiculoResponse;
import io.grpc.stub.StreamObserver;
import multa.v1.MultaOuterClass.MultaResponse;

@GrpcService
public class VeiculoGrpcServiceImpl extends ConsultaVeiculoGrpc.ConsultaVeiculoImplBase {

	private static final Logger log = LoggerFactory.getLogger(VeiculoGrpcServiceImpl.class);

	private final VeiculoServiceImpl veiculoService;
	private final MultaClient multaClient;
	
	public VeiculoGrpcServiceImpl(VeiculoServiceImpl veiculoService, MultaClient multaClient) {
		this.veiculoService = veiculoService;
		this.multaClient = multaClient;
	}
	
	@Override
	public void findByPlaca(VeiculoRequest req, StreamObserver<VeiculoMultaResponse> responseObserver) {

		String placa = req.getPlaca();
		
        log.info("Consulta veículo por placa. placa={}", placa);

        Veiculo veiculo = veiculoService.findByPlaca(placa);
        
        MultaResponse multaResponse = multaClient.listarPorPlaca(placa);
        
        VeiculoMultaResponse veiculoResponse = VeiculoMultaResponse.newBuilder()
    		.setId(veiculo.getId())
    		.setPlaca(veiculo.getPlaca())
    		.setAno(veiculo.getAno())
    		.setMarca(veiculo.getMarca())
    		.setModelo(veiculo.getModelo())
    		.setCor(veiculo.getCor())
    		.addAllListaMultas(multaResponse.getListaMultasList())
    		.setCpfProprietario(veiculo.getCpfProprietario())
    		.build();
        
        log.info("Enviando resposta com {} multas", veiculoResponse.getListaMultasCount());
        
        responseObserver.onNext(veiculoResponse);
        responseObserver.onCompleted();
		
	}
	
	@Override
	public void findByProprietario(VeiculoRequest req, StreamObserver<ListaVeiculoResponse> responseObserver) {
		
		String placa = req.getPlaca();
		
        log.info("Consulta veículo por placa. placa={}", placa);
        
        List<VeiculoResponse> listaVeiculo = List.of(
    		VeiculoResponse.newBuilder().setId(1).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(2).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(3).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(4).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(5).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(6).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build());
        
        ListaVeiculoResponse listVeiculoResponse = ListaVeiculoResponse.newBuilder()
        		.addAllListaVeiculos(listaVeiculo).build();
        
        responseObserver.onNext(listVeiculoResponse);
        responseObserver.onCompleted();
	}
	
	
	 @Override
	 public void findAll(Empty req, StreamObserver<ListaVeiculoResponse> responseObserver) {
		 
		 log.info("Listando todos as veículos...");
		 
		 List<VeiculoResponse> listaVeiculo = List.of(
			VeiculoResponse.newBuilder().setId(1).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(2).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(3).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(4).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(5).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build(),
    		VeiculoResponse.newBuilder().setId(6).setPlaca("ptl-3564").setAno("2018").setMarca("Volkswagem").setModelo("Gol 1.0").setCor("branco").build());
		        
        ListaVeiculoResponse listVeiculoResponse = ListaVeiculoResponse.newBuilder()
        		.addAllListaVeiculos(listaVeiculo).build();
        
        responseObserver.onNext(listVeiculoResponse);
        responseObserver.onCompleted();
		 
	 }

	
}

package com.algaworks.api.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.api.domain.model.Cliente;
import com.algaworks.api.domain.model.Entrega;
import com.algaworks.api.domain.model.StatusEntrega;
import com.algaworks.api.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
	
	private EntregaRepository entregaRepository; 
	private CatalogoClienteService catalogoClienteService; 
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		return entregaRepository.save(entrega);
	}
	
	

}

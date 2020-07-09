package com.ti89.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti89.cursomc.domain.Pedido;
import com.ti89.cursomc.repositories.PedidoRepository;
import com.ti89.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
		
		@Autowired
		private PedidoRepository repo;
		
		
		public Pedido find(Integer id) {
			Optional<Pedido> obj = repo.findById(id);
			
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: "
					+ Pedido.class.getName()
					));
		}
		

}

package com.ti89.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti89.cursomc.domain.Cliente;
import com.ti89.cursomc.repositories.ClienteRepository;
import com.ti89.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
		
		@Autowired
		private ClienteRepository repo;
		
		
		public Cliente find(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: "
					+ Cliente.class.getName()
					));
		}
		

}

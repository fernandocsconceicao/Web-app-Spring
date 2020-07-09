package com.ti89.cursomc.services;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ti89.cursomc.domain.Categoria;
import com.ti89.cursomc.repositories.CategoriaRepository;
import com.ti89.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
		
		@Autowired
		private CategoriaRepository repo;
		
		
		public Categoria find(Integer id) {
			Optional<Categoria> obj = repo.findById(id);
			
			return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! Id: " + id + ", Tipo: "
					+ Categoria.class.getName()
					));
		}
		public Categoria insert (Categoria obj) {
			obj.setId(null);   		
			/*
			 *  se o id do novo objeto nao for nulo
				o save vai consederar uma atualizacao ,
				 e nao 	uma insercao	
			 */
			
			return repo.save(obj);
		}
		public Categoria update(Categoria obj) {
			find(obj.getId());
			return repo.save(obj);			//save ser tanto para salvar quanto atualizar*
		}
		
		
		

}
;
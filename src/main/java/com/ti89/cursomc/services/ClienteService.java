package com.ti89.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ti89.cursomc.domain.Cliente;
import com.ti89.cursomc.domain.Cliente;
import com.ti89.cursomc.dto.ClienteDTO;
import com.ti89.cursomc.repositories.ClienteRepository;
import com.ti89.cursomc.services.exceptions.DataIntegrityException;
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
		
		public Cliente update(Cliente obj) {
			Cliente newObj=find(obj.getId());
			updateData(newObj,obj);
			return repo.save(newObj);			//save ser tanto para salvar quanto atualizar*
		}
		
		public void delete(Integer id) {
			find(id);
			try {
				repo.deleteById(id);		}
			catch(DataIntegrityViolationException e) {
				throw new DataIntegrityException("Nao é possivel excluir um cliente "
						+ "que possui pedidos ou enderecos") ;	}
			
			
		
		}
		public List<Cliente> findAll(){
			return repo.findAll();
				
		}
		
		public Page<Cliente > findPage(Integer page, Integer linesPerPage, String orderBy, String direction ){
			PageRequest pageRequest = PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy);
		 
		
			return repo.findAll(pageRequest) ;
		}
		
		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(),objDto.getEmail(),objDto.getEmail(),null,null);
			
		}
		private void updateData(Cliente newObj,Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
		
}

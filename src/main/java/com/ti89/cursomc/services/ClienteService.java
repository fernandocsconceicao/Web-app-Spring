package com.ti89.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ti89.cursomc.domain.Cidade;
import com.ti89.cursomc.domain.Cliente;
import com.ti89.cursomc.domain.Endereco;
import com.ti89.cursomc.domain.enums.TipoCliente;
import com.ti89.cursomc.dto.ClienteDTO;
import com.ti89.cursomc.dto.ClienteNewDTO;
import com.ti89.cursomc.repositories.ClienteRepository;
import com.ti89.cursomc.repositories.EnderecoRepository;
import com.ti89.cursomc.services.exceptions.DataIntegrityException;
import com.ti89.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
		
		@Autowired
		private ClienteRepository repo;
		@Autowired
		private EnderecoRepository enderecoRepository;

		
		
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
		public Cliente insert (Cliente obj) {
			obj.setId(null);   		
			/*
			 *  se o id do novo objeto nao for nulo
				o save vai consederar uma atualizacao ,
				 e nao 	uma insercao	
			 */
			obj=repo.save(obj);
			enderecoRepository.saveAll(obj.getEnderecos());
			
			return obj;
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
		
		public Cliente fromDTO(ClienteNewDTO objDto) {
			Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()));
			Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
			Endereco end = new Endereco(null,objDto.getLogradouro(),objDto.getNumero(),objDto.getComplemento(),objDto.getBairro(),objDto.getCep(),cli,cid);
			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDto.getTelefone1());
			if(objDto.getTelefone1()!=null) {
				cli.getTelefones().add(objDto.getTelefone2());
			}
			if(objDto.getTelefone2()!=null) {
				cli.getTelefones().add(objDto.getTelefone3());
			}
			return cli;
		
			
		}
		
		private void updateData(Cliente newObj,Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
		
}

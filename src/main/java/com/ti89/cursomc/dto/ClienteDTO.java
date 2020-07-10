package com.ti89.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ti89.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length( min=5, max=120,  message= "O tamanho deve ser entre 5 e 120 caractéres")
	private String nome;
	@NotEmpty( message= "Preencimento obrigatorio")
	@Email(message= "email inválido")
	private String email;
	public ClienteDTO() {
		 
	 }
	public ClienteDTO(Cliente obj) {
		id=obj.getId();
		nome=obj.getNome();
		email=obj.getEmail();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
	

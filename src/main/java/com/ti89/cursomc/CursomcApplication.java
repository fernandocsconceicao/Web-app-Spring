package com.ti89.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ti89.cursomc.domain.Categoria;
import com.ti89.cursomc.domain.Cidade;
import com.ti89.cursomc.domain.Cliente;
import com.ti89.cursomc.domain.Endereco;
import com.ti89.cursomc.domain.Estado;
import com.ti89.cursomc.domain.Produto;
import com.ti89.cursomc.domain.enums.TipoCliente;
import com.ti89.cursomc.repositories.CategoriaRepository;
import com.ti89.cursomc.repositories.CidadeRepository;
import com.ti89.cursomc.repositories.ClienteRepository;
import com.ti89.cursomc.repositories.EnderecoRepository;
import com.ti89.cursomc.repositories.EstadoRepository;
import com.ti89.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1= new Categoria(null,"Informática");
		Categoria cat2= new Categoria(null,"Escritorio");
		Produto p1= new Produto(null, "Computador", 2000.00);
		Produto p2= new Produto(null, "Impressora", 400.00);
		Produto p3= new Produto(null, "Mouse", 20.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));	
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		

		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1= new Estado(null, "Minas Gerais");
		Estado est2= new Estado(null, "Sao Paulo");
		
		
		Cidade c1 = new Cidade( null, "Uberlandia", est1);
		Cidade c2 = new Cidade ( null, "Sao paulo", est2);
		Cidade c3 = new Cidade ( null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est1.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "mariadochatuol@uol.com.br"
				,"34781947358", TipoCliente.PESSOAFISICA); 
		cli1.getTelefones().addAll(Arrays.asList("27274643", "936127468"));
		
		Endereco e1= new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2	= new Endereco(null, "Rua 2", "1077", "casa 56", "Uberaba", "1298737", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}

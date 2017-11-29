package br.com.cauejannini;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cauejannini.domain.Categoria;
import br.com.cauejannini.repositories.CategoriaRepository;

@SpringBootApplication
public class Projeto2Application implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Projeto2Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática"); 
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.save(Arrays.asList(cat1, cat2));
		
	}
}

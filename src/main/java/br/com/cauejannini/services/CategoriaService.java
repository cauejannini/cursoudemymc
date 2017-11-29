package br.com.cauejannini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cauejannini.domain.Categoria;
import br.com.cauejannini.repositories.CategoriaRepository;
import br.com.cauejannini.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria cat = repo.findOne(id);
		if (cat == null) {
			throw new ObjectNotFoundException("Objeto de tipo "+Categoria.class.getName()+" não encontrado para o ID: "+id);
		}
		return cat;
	}
}

package br.com.cauejannini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cauejannini.domain.Cliente;
import br.com.cauejannini.repositories.ClienteRepository;
import br.com.cauejannini.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente cliente = repo.findOne(id);
		if (cliente == null) {
			throw new ObjectNotFoundException("Objeto de tipo "+Cliente.class.getName()+" não encontrado para o ID: "+id);
		}
		return cliente;
	}
}

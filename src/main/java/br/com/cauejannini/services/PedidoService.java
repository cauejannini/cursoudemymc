package br.com.cauejannini.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cauejannini.domain.Pedido;
import br.com.cauejannini.repositories.PedidoRepository;
import br.com.cauejannini.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido cat = repo.findOne(id);
		if (cat == null) {
			throw new ObjectNotFoundException("Objeto de tipo "+Pedido.class.getName()+" não encontrado para o ID: "+id);
		}
		return cat;
	}
}

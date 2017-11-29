package br.com.cauejannini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cauejannini.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}

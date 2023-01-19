package apijavatt.model.repositorys;

import org.springframework.data.repository.CrudRepository;

import apijavatt.model.entitys.Pessoa;

public interface PessoaRepository 
	extends CrudRepository<Pessoa, Integer>{
	
	public Iterable<Pessoa> findByNameContaining(String parteNome);
}

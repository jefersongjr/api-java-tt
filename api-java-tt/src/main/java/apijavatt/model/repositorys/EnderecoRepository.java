package apijavatt.model.repositorys;

import org.springframework.data.repository.CrudRepository;

import apijavatt.model.entitys.Endereco;
import apijavatt.model.entitys.Pessoa;

public interface EnderecoRepository 
		extends CrudRepository<Endereco, Integer>{

    public Iterable<Endereco> findByPessoaId(int pessoaId);

}

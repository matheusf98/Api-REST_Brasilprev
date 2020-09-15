package br.com.brasilprev.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.brasilprev.models.Collaborator;

public interface CollaboratorRepository extends CrudRepository<Collaborator, String>{
	Collaborator findByid(long id);
}

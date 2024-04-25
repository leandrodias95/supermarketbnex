package br.com.supermarketbnex.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.supermarketbnex.model.entity.User;

public interface UserRepository extends JpaRepository<User, String>  {

	UserDetails findByLogin(String login);
	
}

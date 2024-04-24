package br.com.supermarketbnex.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.supermarketbnex.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{


}

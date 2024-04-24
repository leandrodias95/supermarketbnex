package br.com.supermarketbnex.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.supermarketbnex.model.entity.Product;
import br.com.supermarketbnex.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository; 
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public Optional<Product> searchById(Long id){
		return repository.findById(id);
		
	}
	
	public void deleteProduct(Long id) {
		repository.findById(id)
		.map(product -> {
			repository.delete(product); 
			return product;
		}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
	}
	
	public void updateProduct(Long id, Product productUpdate) {
		repository.findById(id)
		.map(product -> {
			product.setId(productUpdate.getId());
			repository.save(product); 
			return product;
		}).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!"));
	}
	
	public List<Product> listAllProducts() {
	    return repository.findAll();
	}
}

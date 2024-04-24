package br.com.supermarketbnex.model.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.supermarketbnex.model.entity.Product;
import br.com.supermarketbnex.model.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/supermaketbnex/product")
public class ProductController {
	
	private final ProductService productService;
	 
	@Operation(summary = "Insert Product")
	@PostMapping(value="insert")
	@ResponseStatus(HttpStatus.CREATED)
	public Product saveProduct(@RequestBody @Valid Product product) {
		return productService.saveProduct(product);
	}
	
	@Operation(summary = "Search Product by id")
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Optional<Product> searchById(@PathVariable Long id) {
		return productService.searchById(id);
	}
	
	@Operation(summary = "Delete Product by id")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteProduct(@PathVariable Long id) {
		 productService.deleteProduct(id);
	}
	
	@Operation(summary = "Update Product by id")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateProduct(@PathVariable Long id, @RequestBody @Valid Product productUpdate) {
		 productService.updateProduct(id, productUpdate);;
	}
	
	@Operation(summary = "List all Products")
	@GetMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	public List<Product> listAllProducts() {
		return	productService.listAllProducts();
	}
}

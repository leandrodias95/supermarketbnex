package br.com.supermarketbnex.model.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.supermarketbnex.model.entity.AuthenticationDTO;
import br.com.supermarketbnex.model.entity.LoginResponseDTO;
import br.com.supermarketbnex.model.entity.RegisterDTO;
import br.com.supermarketbnex.model.entity.User;
import br.com.supermarketbnex.model.repository.UserRepository;
import br.com.supermarketbnex.model.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/supermaketbnex")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final UserRepository repository;
	private final TokenService tokenService;
	
	@PostMapping(value="login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		var token =  tokenService.genereateToken((User) auth.getPrincipal());
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping(value="register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data ) {
		if(this.repository.findByLogin(data.login())!= null) return ResponseEntity.badRequest().build();
		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		User newUser = new User(data.login(), encryptedPassword, data.role());
		this.repository.save(newUser);
		return ResponseEntity.ok().build();
	}
}

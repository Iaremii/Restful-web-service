package com.example.Restfulwebservices.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.Restfulwebservices.entity.Client;
import com.example.Restfulwebservices.exception.ClientNotFountException;
import com.example.Restfulwebservices.repository.ClientRepository;
import com.example.Restfulwebservices.repository.CommissionRepository;
import com.example.Restfulwebservices.repository.ProjectRepository;

@RestController
@RequestMapping("/jpa")
public class JPAController {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private CommissionRepository commissionRepository;
	
	@GetMapping("/clients")
	public List<Client> retrieveAllClients() {
		return clientRepository.findAll();
	}
	
	@GetMapping("/clients/{id}")
	public Resource<Client> retrieveClient(@PathVariable int id) {
		Optional<Client> client = clientRepository.findById(id);
		if (!client.isPresent()   ) {
			throw new ClientNotFountException("id-" + id);
		}
		Resource<Client> resource = new Resource<Client>(client.get());
		
		//HATEOAS
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllClients());
		
		resource.add(linkTo.withRel("all-clients"));
		
		
		
		return resource;
	}
	
	@PostMapping("/clients")
	public ResponseEntity <Object> saveClients(@Valid @RequestBody Client client) {
		Client clientSaved = clientRepository.save(client);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientSaved.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/clients/{id}")
	public void deleteClients(@PathVariable int id) {
		clientRepository.deleteById(id);
		
	}
	
	@PutMapping("/clients/{id}")
	public void updateClients(@PathVariable int id, @RequestBody Client client) {
		Client newClient = clientRepository.getOne(id);
		newClient.setFirstName(client.getFirstName());
		newClient.setLastName(client.getLastName());
		newClient.setCompany(client.getCompany());
		newClient.setEmail(client.getEmail());
		newClient.setPhoneNumber(client.getPhoneNumber());;
		clientRepository.save(newClient);
	}

	

}

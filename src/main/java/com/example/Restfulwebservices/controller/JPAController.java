package com.example.Restfulwebservices.controller;


import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllClients());
		
		resource.add(linkTo.withRel("all-clients"));
		
		//HATEOAS
		
		return resource;
	}

}

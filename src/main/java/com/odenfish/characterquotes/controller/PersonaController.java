package com.odenfish.characterquotes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odenfish.characterquotes.dto.PersonaDTO;
import com.odenfish.characterquotes.services.PersonaService;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	PersonaService personaService;
	
	@GetMapping("/characters")
	public ResponseEntity<List<PersonaDTO>> getAllPersona() {
		ResponseEntity<List<PersonaDTO>> response = null;
		
		try {
			
			List<PersonaDTO> personaDTOList = personaService.list();
			if (personaDTOList.isEmpty()) {
				
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				
				response = new ResponseEntity<>(personaDTOList, HttpStatus.OK);
			}
			
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@GetMapping("/characters/{id}")
	public ResponseEntity<PersonaDTO> getPersonaById(@PathVariable("id") long id) {
		
		
		ResponseEntity<PersonaDTO> response = null;
		
		try {
			
			PersonaDTO personaDTO = personaService.getById(id);
			
			if (personaDTO != null) {
				
				response = new ResponseEntity<>(personaDTO, HttpStatus.OK);
			}
			else {
				
				response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	@PostMapping("/characters")
	public ResponseEntity<List<PersonaDTO>> createPersona(@RequestBody PersonaDTO newPersona) {
		
		ResponseEntity<List<PersonaDTO>> response = null;
		
		try {
			
			List<PersonaDTO> personaDTOList = personaService.save(newPersona);
			
			if (personaDTOList.isEmpty()) {
				
				response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			else {
				
				response = new ResponseEntity<>(personaDTOList, HttpStatus.CREATED);
			}
		}
		catch(Exception e) {
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
}

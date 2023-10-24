package com.odenfish.characterquotes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odenfish.characterquotes.dto.PersonaDTO;
import com.odenfish.characterquotes.services.PersonaService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	PersonaService personaService;
	
	@GetMapping("/characters")
	public ResponseEntity<Map<String, PersonaDTO>> getAllPersona() {
		ResponseEntity<Map<String, PersonaDTO>> response = null;
		
		try {
			
			List<PersonaDTO> personaDTOList = personaService.list();
			if (personaDTOList.isEmpty()) {
				
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			else {
				
				response = new ResponseEntity<>(getResponseMap(personaDTOList), HttpStatus.OK);
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
	public ResponseEntity<Map<String, PersonaDTO>> createPersona(@RequestBody PersonaDTO newPersona) {
		
		ResponseEntity<Map<String, PersonaDTO>> response = null;
		
		try {
			
			personaService.save(newPersona);
			List<PersonaDTO> personaDTOList = personaService.list();
			
			if (personaDTOList.isEmpty()) {
				
				response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			else {
				
				response = new ResponseEntity<>(getResponseMap(personaDTOList), HttpStatus.CREATED);
			}
		}
		catch(Exception e) {
			System.out.println("[ERROR]: " + e.getMessage());
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@PutMapping("/characters/{id}")
	public ResponseEntity<Map<String, PersonaDTO>> updatePersona(@RequestBody PersonaDTO personaDTO, 
																 @PathVariable("id") long id) {
			
		ResponseEntity<Map<String, PersonaDTO>> response = null;
		
		try {
			PersonaDTO updatedPersonaDTO = personaService.update(personaDTO);
			List<PersonaDTO> personaDTOList = personaService.list();
			if (updatedPersonaDTO.equals(personaDTO)) {
				
				response = new ResponseEntity<>(getResponseMap(personaDTOList), HttpStatus.CREATED);
				
			}
			else {
				response = new ResponseEntity<>(getResponseMap(personaDTOList), HttpStatus.NOT_FOUND);
				
			}
		}
		catch(Exception e) {
			System.out.println("[ERROR]: " + e.getMessage());
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	@DeleteMapping("/characters/{id}")
	public ResponseEntity<Map<String, PersonaDTO>> deletePersona(@PathVariable("id") long id) {
		
		ResponseEntity<Map<String, PersonaDTO>> response = null;
		
		try {
			personaService.delete(id);
			List<PersonaDTO> personaDTOList = personaService.list();
			
			if (personaDTOList.isEmpty()) {
				
				response = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				
			}
			else {
				response = new ResponseEntity<>(getResponseMap(personaDTOList), HttpStatus.OK);
				
			}
			
		}
		catch (Exception e) {
			System.out.println("[ERROR]: " + e.getMessage());
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	private Map<String, PersonaDTO> getResponseMap(List<PersonaDTO> personaDTOList) {
		
		Map<String, PersonaDTO> responseMap = new HashMap<>();
		personaDTOList.forEach(personaDTO -> {
			responseMap.put(personaDTO.getId().toString(), personaDTO);
		});
		
		return responseMap;
		
	}
	
}

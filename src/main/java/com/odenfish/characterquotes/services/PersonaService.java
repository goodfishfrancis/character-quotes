package com.odenfish.characterquotes.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odenfish.characterquotes.models.Persona;
import com.odenfish.characterquotes.repositories.PersonaRepository;
import com.odenfish.characterquotes.repositories.QuoteRepository;

@Service
public class PersonaService {
	
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	QuoteRepository quoteRepository;
	
	public List<Persona> list() {
        return personaRepository.findAll();
    }
	
	
	public Persona getById(Long id) {
		
		return personaRepository.findById(id).get();
	}
}

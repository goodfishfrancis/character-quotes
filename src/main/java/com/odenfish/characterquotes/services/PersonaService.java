package com.odenfish.characterquotes.services;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odenfish.characterquotes.dto.PersonaDTO;
import com.odenfish.characterquotes.dto.QuoteDTO;
import com.odenfish.characterquotes.models.Persona;
import com.odenfish.characterquotes.models.Quote;
import com.odenfish.characterquotes.repositories.PersonaRepository;
import com.odenfish.characterquotes.repositories.QuoteRepository;

@Service
public class PersonaService {
	
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	QuoteRepository quoteRepository;
	
	
	// This method returns all persona objects in the database
	public List<PersonaDTO> list() {		
		return this.getPersonaDTOList();
    }
	
	
	// This method takes an id for a persona and returns the personaDTO
	public PersonaDTO getById(Long id) {
		
		PersonaDTO personaDTO = null;
		
		if (personaRepository.existsById(id)) {
			
			Persona persona = personaRepository.findById(id).get();
			List<QuoteDTO> quoteDTOList = this.getQuoteDTOList(persona.getQuotes()); 
			 personaDTO = new PersonaDTO(persona.getId(), 
										 persona.getName(), 
										 quoteDTOList);
		}
		return personaDTO;
	}
	
	
	// this method takes a PersonaDTO and saves a new Persona
	public void save(PersonaDTO newPersona) {
		
		try {
			
			// We save a new persona
			Persona persona = new Persona();
			persona.setName(newPersona.getName());
			
			
			// If there are new quotes to save, save them as well with new persona id
			if (newPersona.getQuotes() != null) {
				List<QuoteDTO> quoteDTOList = newPersona.getQuotes();
				List<Quote> quoteList = new ArrayList<>();
				
				for (QuoteDTO quote: quoteDTOList) {
					Quote newQuote = new Quote();
					newQuote.setQuote(quote.getQuote());
					quoteList.add(newQuote);
				}
				
				persona.setQuotes(quoteList);
				persona = personaRepository.saveAndFlush(persona);
				
			}
			
		}
		catch (Exception e) {
			System.out.println("[ERROR]: " + e.getMessage());
		}
		
	}
	
	
	// this method updates an existing persona
	public List<PersonaDTO> update(PersonaDTO personaDTO) {
		
		// TODO
		
		
		return this.getPersonaDTOList();
	}
	
	// this method deletes an existing persona
	public List<PersonaDTO> delete(Long id) {
		
		// TODO
		
		
		return this.getPersonaDTOList();
	}
	
	
	
	
	/*********************************
	 *                               *
	 *  HELPER METHODS               *
	 * 								 *
	 * 								 *
	 * 								 *
	 * 								 *
	 * 								 *
	 * 								 *
	 *********************************/
	
	
	// This takes a list of quotes and returns a list of quoteDTOs
	private List<QuoteDTO> getQuoteDTOList(List<Quote> quoteList) {
		
		List<QuoteDTO> quoteDTOList = new ArrayList<>();
		
		try {
			
			for (Quote quote : quoteList) {
				quoteDTOList.add(new QuoteDTO(quote.getId(), quote.getQuote()));
			}
		}
		catch (Exception e) {
			System.out.println("[ERROR]: " + e.getMessage());
		}
		
		
		return quoteDTOList;
	}
	
	
	// This gets all persona and returns a list of PersonaDTOs
	private List<PersonaDTO> getPersonaDTOList() {
		
		List<PersonaDTO> personaDTOList = new ArrayList<>();
		
		try {
			List<Persona> personaList = personaRepository.findAll();
			
			// For each persona in the list, get quotes and create list of quoteDTOs
			// Then for each persona create a personaDTO and create list of personaDTOs
			for (Persona persona : personaList) 
			{ 
				
				List<Quote> quoteList = persona.getQuotes();
				List<QuoteDTO> quoteDTOList = this.getQuoteDTOList(quoteList);
			    PersonaDTO personaDTO = new PersonaDTO(persona.getId(), 
			    									   persona.getName(),
			    									   quoteDTOList);
			    personaDTOList.add(personaDTO);
			}
			
		}
		catch (Exception e) {
			System.out.println("[ERROR]: " + e.getMessage());
		}
		
		
        return personaDTOList;
	}
}

package com.odenfish.characterquotes;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.odenfish.characterquotes.models.Persona;
import com.odenfish.characterquotes.models.Quote;
import com.odenfish.characterquotes.services.PersonaService;

@SpringBootTest
class CharacterQuotesApplicationTests {
	
	@Autowired
	PersonaService personaService;

	@Test
	void PersonaServiceListTest() {
		
		List<Persona> personaList = personaService.list();
		String name = "Mario";
		
		for (int i = 0; i < personaList.size(); i++) {
			System.out.println(personaList.get(i).getName() + ": ");
			List<Quote> quoteList = personaList.get(i).getQuotes();
			
			for (int j = 0; j < quoteList.size(); j++) {
				System.out.println("'" + quoteList.get(j).getQuote() + "'");
			}
			
		}
		
		Persona persona = personaList.get(0);
		Assert.isTrue(persona.getName().equalsIgnoreCase(name), "[FAIL] " 
		+ persona.getName() + " does not equal " + name);
	}
	
	@Test
	void PersonaQuoteTest() {
		List<Persona> personaList = personaService.list();
		Persona mario = personaList.get(0);
		String marioQuote = "It's a me, Mario!";
		System.out.println(mario.getQuotes().get(0).getQuote());
		
		Assert.isTrue(mario.getQuotes().get(0).getQuote().equalsIgnoreCase(marioQuote), 
				"[FAIL] quote does note equal '" + marioQuote + "'");
		
	}
	
	@Test
	void PersonaServiceGetByIdTest() {
		
		// Mario Id = 1
		Long mario_id = (long) 1;
		String mario_name = "Mario";
		
		Persona persona = personaService.getById(mario_id);
		System.out.println(persona.getName());
		
		Assert.isTrue(persona.getName().equalsIgnoreCase(mario_name), 
				"[FAIL] Name does not equal " + mario_name);
	}

}

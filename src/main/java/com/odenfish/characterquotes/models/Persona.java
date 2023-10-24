package com.odenfish.characterquotes.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PERSONA")
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8247801434977694871L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="name")
	private String name;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="persona", cascade = CascadeType.ALL)
	@OrderBy
	private List<Quote> quotes;
	
	public void setQuotes(List<Quote> quoteList) {
		this.quotes = quoteList;
		quotes.forEach(quote -> quote.setPersona(this));
	}

}

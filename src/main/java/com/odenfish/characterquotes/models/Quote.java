package com.odenfish.characterquotes.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="QUOTE")
public class Quote {
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="quote")
	private String quote;
	
	@ManyToOne
    @JoinColumn(name="character_id", nullable=false)
	private Character character;

}

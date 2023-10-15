package com.odenfish.characterquotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odenfish.characterquotes.models.Persona;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

}

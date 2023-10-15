package com.odenfish.characterquotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odenfish.characterquotes.models.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long>{

}

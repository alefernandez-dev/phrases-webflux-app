package dev.alejandro.phraseswebfluxapp.phrase;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "PHRASES")
public record Phrase(
        @Id
        Long id,
        String text,
        String author,
        String nationality
) {}

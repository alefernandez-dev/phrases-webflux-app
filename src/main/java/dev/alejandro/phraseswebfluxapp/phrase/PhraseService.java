package dev.alejandro.phraseswebfluxapp.phrase;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class PhraseService {

    private final Phrases phrases;

    public PhraseService(Phrases phrases) {
        this.phrases = phrases;
    }

    public Flux<Phrase> getPhrases(String authorName, int limit) {
        if (authorName == null) {
            return phrases.listPhrases(limit);
        }
        return phrases.listPhraseByAuthorName(authorName, limit);
    }

    public Flux<Phrase> getByAuthorNationality(String authorNationality, int limit) {
        return phrases.listPhrasesByAuthorNationality(authorNationality, limit);
    }
}

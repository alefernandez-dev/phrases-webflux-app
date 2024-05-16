package dev.alejandro.phraseswebfluxapp.phrase;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PhraseHandler {

    private final PhraseService phraseService;

    public PhraseHandler(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    public Mono<ServerResponse> getPhrases(ServerRequest request) {
        int limit = Integer.parseInt(request.queryParam("limit").orElse("10"));
        String authorName = request.queryParam("name").orElse(null);
        var phrases = phraseService.getPhrases(authorName, limit);
        return ServerResponse
                .ok()
                .body(phrases, Phrase.class);
    }

    public Mono<ServerResponse> getPhrasesByAuthorNationality(ServerRequest request) {
        int limit = Integer.parseInt(request.queryParam("limit").orElse("10"));
        String authorNationality = request.pathVariable("nationality");
        var phrases = phraseService.getByAuthorNationality(authorNationality, limit);
        return ServerResponse
                .ok()
                .body(phrases, Phrase.class);
    }



}

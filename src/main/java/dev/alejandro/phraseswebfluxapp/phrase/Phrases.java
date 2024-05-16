package dev.alejandro.phraseswebfluxapp.phrase;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface Phrases extends ReactiveCrudRepository<Phrase, Long> {

    @Query("SELECT * FROM PHRASES LIMIT :limit")
    Flux<Phrase> listPhrases(@Param("limit") int limit);

    @Query("SELECT * FROM PHRASES WHERE author LIKE CONCAT('%', :authorName, '%') LIMIT :limit")
    Flux<Phrase> listPhraseByAuthorName(@Param("authorName") String authorName, @Param("limit") int limit);

    @Query("SELECT * FROM PHRASES WHERE nationality = :nationality LIMIT :limit")
    Flux<Phrase> listPhrasesByAuthorNationality(@Param("nationality")String nationality, @Param("limit") int limit);

}

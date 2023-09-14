package com.dh.catalogservice.repositories;
import com.dh.catalogservice.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesRepository extends MongoRepository<Movie, Long> {

    public List<Movie> findByGenre(String genre);
}

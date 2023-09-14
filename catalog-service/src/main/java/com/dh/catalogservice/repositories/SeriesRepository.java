package com.dh.catalogservice.repositories;

import com.dh.catalogservice.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeriesRepository extends MongoRepository<Serie, Long> {

    public List<Serie> findByGenre(String genre);
}

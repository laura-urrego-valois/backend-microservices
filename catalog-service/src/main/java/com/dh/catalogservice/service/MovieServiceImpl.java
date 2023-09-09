package com.dh.catalogservice.service;

import com.dh.catalogservice.client.IMovieClient;
import com.dh.catalogservice.model.Movie;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MovieServiceImpl implements IMovieService {

    private IMovieClient movieClient;

    @Autowired
    public MovieServiceImpl(IMovieClient movieClient) {
        this.movieClient = movieClient;
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "findAllEmpty")
    @Retry(name = "movies")
    @Override
    public List<Movie> findAll() {
        log.info("Calling movie service...");
        return movieClient.findAll(true);
    }

    private List<Movie> findAllEmpty(CallNotPermittedException exception) {

        return new ArrayList<>();
    }
}

package com.dh.catalogservice.service;

import com.dh.catalogservice.client.ISerieClient;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
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
public class SerieServiceImpl implements ISerieService {

    private ISerieClient serieClient;

    @Autowired
    public SerieServiceImpl(ISerieClient serieClient) {
        this.serieClient = serieClient;
    }
    @CircuitBreaker(name = "series", fallbackMethod = "findAllEmpty")
    @Retry(name = "series")
    @Override
    public List<Serie> findAll() {
        log.info("Calling serie service...");

        return serieClient.findAll(true);
    }

    private List<Serie> findAllEmpty(CallNotPermittedException exception) {

        return new ArrayList<>();
    }
}

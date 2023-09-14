package com.dh.catalogservice.client;

import com.dh.catalogservice.config.ConfiguracionBalanceador;
import com.dh.catalogservice.model.Serie;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "serie-service")
@LoadBalancerClient(name = "serie-service", configuration = ConfiguracionBalanceador.class)
public interface ISerieClient {
    @GetMapping("/api/v1/series/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre);

    @GetMapping("/api/v1/series/findAll")
    List<Serie> findAll(@RequestParam Boolean throwError);

    @PostMapping("/api/v1/series")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie);
}

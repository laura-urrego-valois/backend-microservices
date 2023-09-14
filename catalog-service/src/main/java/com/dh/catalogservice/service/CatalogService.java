package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Genre;
import com.dh.catalogservice.model.Movie;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repositories.MoviesRepository;
import com.dh.catalogservice.repositories.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CatalogService {
    private final MoviesRepository movieRepository;
    private final SeriesRepository seriesRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public Serie save(Serie serie) {
        return seriesRepository.save(serie);
    }

    public Genre getMoviesAndSeries (String genre){
        Genre genre1 = new Genre();
        genre1.setMovies(movieRepository.findByGenre(genre));
        genre1.setSeries(seriesRepository.findByGenre(genre));
        return genre1;
    }

}

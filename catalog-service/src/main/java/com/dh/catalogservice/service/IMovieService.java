package com.dh.catalogservice.service;

import com.dh.catalogservice.model.Movie;

import java.util.List;

public interface IMovieService {

    List<Movie> findAll();
}

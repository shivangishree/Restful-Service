package com.stackroute.movie.service;

import java.util.List;
import java.util.Optional;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;

public interface MovieService {

	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

	public Iterable<Movie> getAllMovies();

	public Boolean delete(int id) throws MovieNotFoundException;

	public Optional<Movie> getMovieById(int id);

	public Movie UpdateMovieById(int id, Movie movie) throws MovieNotFoundException;

	List<Movie> findMovieByTitle(String title);
}

package com.stackroute.movie.service;

import java.util.List;
import java.util.Optional;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;

public class MovieServiceImpl2 implements MovieService{

	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Movie> getAllMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(int id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Movie> getMovieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie UpdateMovieById(int id, Movie movie) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findMovieByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}

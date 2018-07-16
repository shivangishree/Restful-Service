package com.stackroute.movie.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.stackroute.movie.Repository.MovieRepository;
import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;

@Service
@Primary
public class MovieServiceImpl implements MovieService{

    MovieRepository	movieRepository;
    
    @Autowired
    public MovieServiceImpl( MovieRepository movieRepository) {
    	this.movieRepository = movieRepository;
    }
    
	@Override
	public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
		Iterable<Movie> movies= getAllMovies();
		Iterator<Movie> iterator = movies.iterator();
		while(iterator.hasNext()) {
			Movie m = iterator.next();
			if(movie.equals(m)) {
				throw new MovieAlreadyExistsException("Movie already exists");
			}
		}
		return movieRepository.save(movie);
	}

	@Override
	public Iterable<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	@Override
	public Boolean delete(int id) throws MovieNotFoundException {
		Optional<Movie> movie = getMovieById(id);
		if(movie.isPresent()) {
			movieRepository.deleteById(id);
			return true;
		}
		throw new MovieNotFoundException("Movie not found");
	}

	@Override
	public Optional<Movie> getMovieById(int id) {	
		Optional<Movie> movie = movieRepository.findById(id);
		return movie;
	}

	@Override
	public List<Movie> findMovieByTitle(String title){
		List<Movie> movie = movieRepository.findMovieByTitle(title);
		return movie;
	}
	
	@Override
	public Movie UpdateMovieById(int id,Movie movie) throws MovieNotFoundException {
		Optional<Movie> m = getMovieById(id);
		if(m.isPresent()) {
			movie.setId(id);
			return movieRepository.save(movie);	
		}
		throw new MovieNotFoundException("Movie not found");	
	}

}

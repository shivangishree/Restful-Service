package com.stackroute.movie.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movie.domain.Movie;
import com.stackroute.movie.exception.MovieAlreadyExistsException;
import com.stackroute.movie.exception.MovieNotFoundException;
import com.stackroute.movie.service.MovieService;

@RestController
@RequestMapping("/api/v1")
public class MovieController {

	// @Autowired
	// @Qualifier("MovieServiceImpl")
	//we can use either primary or qualifier
	//if u use qualifier it means for this particular object autowire it with this particular class
	//in case of primary it's like bind every object with this class
	MovieService movieService;

	public MovieController() {

	}
    
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/saveMovie")
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		Movie m;
		try {
			m = movieService.saveMovie(movie);
			return new ResponseEntity<Movie>(m, HttpStatus.CREATED);
		} catch (MovieAlreadyExistsException e) {
			return new ResponseEntity<String>("Movie already exist", HttpStatus.ALREADY_REPORTED);
		}

	}

	@GetMapping("/getMovies")
	public ResponseEntity<?> getAllMovie() {
		return new ResponseEntity<Iterable<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
	}

	@RequestMapping(value = { "/getMovieByIdTitle" }, method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByIdTitle(@RequestParam Map<String, String> queryParameter) {
		//movieService.getMovieById(Integer.parseInt(queryParameter.get("id")));
		String title = queryParameter.get("title");
		List<Movie> movies = movieService.findMovieByTitle(title);
		if (!movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);
		}

	@RequestMapping(value = { "/getMovie" }, method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByIdRequestParam(@RequestParam("id") int ReqParamId) {
		Optional<Movie> movie = movieService.getMovieById(ReqParamId);
		if (movie.isPresent()) {
			return new ResponseEntity<Optional<Movie>>(movie, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = { "/getMovieById/{id}" }, method = RequestMethod.GET)
	public ResponseEntity<?> getMovieByIdPathVariable(@PathVariable("id") int pathVarId) {
		Optional<Movie> movie = movieService.getMovieById(pathVarId);
		if (movie.isPresent()) {
			return new ResponseEntity<Optional<Movie>>(movie, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deleteMovie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable("id") int id) {
		try {
			movieService.delete(id);
			return new ResponseEntity<String>("Deleted Movie", HttpStatus.OK);
		} catch (MovieNotFoundException mnfe) {
			return new ResponseEntity<String>("Movie not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateMovieById/{id}")
	public ResponseEntity<?> updateMovieById(@PathVariable("id") int id, @RequestBody Movie movie) {
		Movie mov;
		try {
			mov = movieService.UpdateMovieById(id, movie);
			return new ResponseEntity<Movie>(mov, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			return new ResponseEntity<String>("OOPs Movie not found", HttpStatus.NOT_FOUND);
		}
	}
}

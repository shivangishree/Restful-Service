package com.stackroute.movie.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.stackroute.movie.domain.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

	@Query("FROM com.stackroute.movie.domain.Movie movie where movie.title =:title") 
	List<Movie> findMovieByTitle(@Param("title") String title);
}

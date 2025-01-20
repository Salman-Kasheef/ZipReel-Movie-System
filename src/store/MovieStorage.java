package store;

import model.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieStorage {
    private final Map<Integer, Movie> movies;

    public MovieStorage() {
        this.movies = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        if (movies.containsKey(movie.getId())) {
            throw new IllegalArgumentException("Movie with ID " + movie.getId() + " already exists.");
        }
        movies.put(movie.getId(), movie);
    }

    public Movie getMovie(int id) {
        return movies.get(id);
    }

    public Map<Integer, Movie> getAllMovies() {
        return new HashMap<>(movies);
    }
}

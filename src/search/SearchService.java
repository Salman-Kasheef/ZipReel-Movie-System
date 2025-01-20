package search;

import model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {
    private final CacheManager cacheManager;

    public SearchService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public List<Movie> searchByTitle(int userId, String title) {
        Movie movie = cacheManager.getFromL1Cache(userId);
        if (movie != null) {
            System.out.println("Cache hit in L1");
            return List.of(movie);
        }

        List<Movie> movies = cacheManager.getFromL2Cache(title);
        if (movies != null) {
            System.out.println("Cache hit in L2");
            return movies;
        }

        movies = cacheManager.searchMoviesByTitle(title);
        cacheManager.updateL2Cache(title, movies);
        return movies;
    }

    public List<Movie> searchByGenre(int userId, String genre) {
        List<Movie> movies = cacheManager.getFromL2Cache(genre);
        if (movies != null) {
            System.out.println("Cache hit in L2");
            return movies;
        }

        movies = cacheManager.searchMoviesByGenre(genre);
        cacheManager.updateL2Cache(genre, movies);
        return movies;
    }

    public List<Movie> searchByYear(int userId, int year) {
        return cacheManager.searchMoviesByYear(year);
    }

    public List<Movie> searchByRating(int userId, double rating) {
        return cacheManager.searchMoviesByRating(rating);
    }

    public List<Movie> searchMultiFilters(int userId, String genre, int year, double minRating) {
        return cacheManager.searchMoviesByGenre(genre).stream()
                .filter(movie -> (year == 0 || movie.getYear() == year) &&
                        movie.getRating() >= minRating)
                .collect(Collectors.toList());
    }
}

package search;

import caches.L1Cache;
import caches.L2Cache;
import model.Movie;
import store.MovieStorage;

import java.util.List;
import java.util.stream.Collectors;

public class CacheManager {
    private final L1Cache<Integer, Movie> l1Cache;
    private final L2Cache<String, List<Movie>> l2Cache;
    private final MovieStorage movieStore;
    private int l1Hits = 0;
    private int l2Hits = 0;
    private int primaryStoreHits = 0;

    public CacheManager(MovieStorage movieStore) {
        this.l1Cache = new L1Cache<>(5);
        this.l2Cache = new L2Cache<>(20);
        this.movieStore = movieStore;
    }

    public Movie getFromL1Cache(int userId) {
        Movie movie = l1Cache.get(userId);
        if (movie != null) l1Hits++;
        return movie;
    }

    public List<Movie> getFromL2Cache(String searchKey) {
        List<Movie> movies = l2Cache.get(searchKey);
        if (movies != null) l2Hits++;
        return movies;
    }

    public void updateL1Cache(int userId, Movie movie) {
        l1Cache.put(userId, movie);
    }

    public void updateL2Cache(String searchKey, List<Movie> movies) {
        l2Cache.put(searchKey, movies);
    }

    public List<Movie> searchMoviesByGenre(String genre) {
        return movieStore.getAllMovies().values().stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByYear(int year) {
        return movieStore.getAllMovies().values().stream()
                .filter(movie -> movie.getYear() == year)
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByRating(double minRating) {
        return movieStore.getAllMovies().values().stream()
                .filter(movie -> movie.getRating() >= minRating)
                .collect(Collectors.toList());
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movieStore.getAllMovies().values().stream()
                .filter(movie -> movie.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    public void clearCache(String cacheLevel) {
        if ("L1".equalsIgnoreCase(cacheLevel)) {
            l1Cache.clear();
        } else if ("L2".equalsIgnoreCase(cacheLevel)) {
            l2Cache.clear();
        } else {
            throw new IllegalArgumentException("Invalid cache level.");
        }
    }

    public String getCacheStats() {
        int totalSearches = l1Hits + l2Hits + primaryStoreHits;
        return String.format("L1 Hits: %d, L2 Hits: %d, Primary Store Hits: %d, Total Searches: %d",
                l1Hits, l2Hits, primaryStoreHits, totalSearches);
    }
}

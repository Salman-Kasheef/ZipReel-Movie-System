import model.Movie;
import model.User;
import search.CacheManager;
import search.SearchService;
import store.MovieStorage;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieStorage movieStore = new MovieStorage();
        CacheManager cacheManager = new CacheManager(movieStore);
        SearchService searchService = new SearchService(cacheManager);

        // Adding movies
        Movie movie1 = new Movie(1, "Inception", "Sci-Fi", 2010, 9.5);
        Movie movie2 = new Movie(2, "The Dark Knight", "Action", 2018, 9.0);
        Movie movie3 = new Movie(3, "Interstellar", "Sci-Fi", 2004, 8.5);
        Movie movie4 = new Movie(4, "Dunkirk", "War", 2007, 8.2);
        movieStore.addMovie(movie1);
        movieStore.addMovie(movie2);
        movieStore.addMovie(movie3);
        movieStore.addMovie(movie4);

        // Adding users
        User user1 = new User(1, "John", "Action");
        User user2 = new User(2, "Alice", "War");

        // Searching for a movie
        List<Movie> searchResults = searchService.searchByTitle(1, "Dunkirk");
        searchResults.forEach(movie -> System.out.println(movie.getTitle()));
    }
}
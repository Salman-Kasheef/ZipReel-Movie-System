package model;

public class Movie {
    private final int id;
    private final String title;
    private final String genre;
    private final int year;
    private final double rating;

    public Movie(int id, String title, String genre, int year, double rating) {
        if (id <= 0 || title == null || title.isEmpty() || genre == null || genre.isEmpty()) {
            throw new IllegalArgumentException("Invalid movie parameters.");
        }
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                '}';
    }
}

package model;

public class User {
    private final int id;
    private final String name;
    private final String preferredGenre;

    public User(int id, String name, String preferredGenre) {
        if (id <= 0 || name == null || name.isEmpty() || preferredGenre == null || preferredGenre.isEmpty()) {
            throw new IllegalArgumentException("Invalid user parameters.");
        }
        this.id = id;
        this.name = name;
        this.preferredGenre = preferredGenre;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPreferredGenre() {
        return preferredGenre;
    }
}

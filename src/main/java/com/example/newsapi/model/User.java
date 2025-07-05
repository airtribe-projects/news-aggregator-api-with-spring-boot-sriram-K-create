// TODO: Implement User.java
// TODO: Implement User.java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    @NotBlank @Column(unique = true)
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPreferencesJson() {
        return preferencesJson;
    }

    public void setPreferencesJson(String preferencesJson) {
        this.preferencesJson = preferencesJson;
    }

    private String preferencesJson;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> readArticles = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> favoriteArticles = new HashSet<>();
    // Getters/Setters

    public void setFavoriteArticles(Set<String> favoriteArticles) {
        this.favoriteArticles = favoriteArticles;
    }

    public Set<String> getFavoriteArticles() {
        return favoriteArticles;
    }
}

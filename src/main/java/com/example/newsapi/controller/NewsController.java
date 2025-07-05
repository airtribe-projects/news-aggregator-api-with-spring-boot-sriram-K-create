// TODO: Implement NewsController.java
// TODO: Implement NewsController.java
@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired private NewsService newsService;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;

    private User getUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(token);
        return userRepository.findByUsername(username).orElseThrow();
    }

    @GetMapping
    public Mono<String> getNews(HttpServletRequest request) {
        User user = getUser(request);
        return newsService.fetchNews("technology"); // You can extract topic from preferencesJson
    }

    @GetMapping("/search/{keyword}")
    public Mono<String> search(@PathVariable String keyword) {
        return newsService.fetchNews(keyword);
    }

    @PostMapping("/{id}/read")
    public ResponseEntity<?> markRead(HttpServletRequest request, @PathVariable String id) {
        User user = getUser(request);
        user.getReadArticles().add(id);
        userRepository.save(user);
        return ResponseEntity.ok("Marked as read");
    }

    @PostMapping("/{id}/favorite")
    public ResponseEntity<?> markFavorite(HttpServletRequest request, @PathVariable String id) {
        User user = getUser(request);
        user.getFavoriteArticles().add(id);
        userRepository.save(user);
        return ResponseEntity.ok("Marked as favorite");
    }

    @GetMapping("/read")
    public ResponseEntity<Set<String>> getRead(HttpServletRequest request) {
        return ResponseEntity.ok(getUser(request).getReadArticles());
    }

    @GetMapping("/favorites")
    public ResponseEntity<Set<String>> getFavorites(HttpServletRequest request) {
        return ResponseEntity.ok(getUser(request).getFavoriteArticles());
    }
}

// TODO: Implement PreferencesController.java
// TODO: Implement PreferencesController.java
@RestController
@RequestMapping("/api/preferences")
public class PreferencesController {
    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    private User getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(token);
        return userRepository.findByUsername(username).orElseThrow();
    }

    @GetMapping
    public ResponseEntity<String> getPreferences(HttpServletRequest request) {
        return ResponseEntity.ok(getCurrentUser(request).getPreferencesJson());
    }

    @PutMapping
    public ResponseEntity<?> updatePreferences(HttpServletRequest request, @RequestBody String prefsJson) {
        User user = getCurrentUser(request);
        user.setPreferencesJson(prefsJson);
        userRepository.save(user);
        return ResponseEntity.ok("Preferences updated");
    }
}

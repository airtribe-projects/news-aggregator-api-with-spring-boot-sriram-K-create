// TODO: Implement AuthController.java
// TODO: Implement AuthController.java
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired private AuthenticationManager authManager;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest req) {
        if (userRepository.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(req.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest req) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = jwtUtil.generateToken(req.getUsername());
        return ResponseEntity.ok(new JwtResponse(token, req.getUsername()));
    }
}

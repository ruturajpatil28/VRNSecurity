package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import Entities.JwtRequest;
import Entities.User;
import Services.JwtService;
import Services.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest request) {
        User user = userService.findByUsername(request.getUsername());
        if (user == null || !new BCryptPasswordEncoder().matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(403).body("Invalid credentials");
        }
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
}

package Controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin access granted!";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User access granted!";
    }
}

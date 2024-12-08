package klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import klu.services.AdminService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        if (adminService.authenticateAdmin(username, password)) {
            String authToken = generateAuthToken(username); // Generate a token for the user
            return ResponseEntity.ok(authToken); // Send the token in response
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
    private String generateAuthToken(String username) {
        return "token-" + username;
    }
}

package klu.controller;

import klu.model.Menu;
import klu.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/menu")
@CrossOrigin(origins = "http://localhost:3000") // Allow cross-origin for the frontend
public class MenuController {

    @Autowired
    private MenuService menuService;

    // Absolute path for the uploads directory
    private static final String UPLOAD_DIR = "C:/Users/DEEPAK/Downloads/Telegram Desktop/deepakjfsd/Eventmanagement/uploads/";

    @GetMapping("/all")
    public ResponseEntity<List<Menu>> getAllMenuItems() {
        List<Menu> menuItems = menuService.getAllMenuItems();
        return ResponseEntity.ok(menuItems);
    }

    @PostMapping("/add")
    public ResponseEntity<Menu> addMenuItem(
            @RequestParam("itemName") String itemName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("file") MultipartFile file) {

        try {
            // Save the file and get the file path
            String filePath = saveFile(file);

            // Create Menu object
            Menu menu = new Menu();
            menu.setItemName(itemName);
            menu.setDescription(description);
            menu.setPrice(price);
            menu.setPictureUrl("/uploads/" + filePath); // Serve relative path

            // Save the menu item in the database
            Menu savedMenu = menuService.addMenuItem(menu);
            return ResponseEntity.ok(savedMenu);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file");
        }

        // Generate unique filename
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Create directory if it doesn't exist
        Path uploadDir = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Save file to the uploads directory
        Path filePath = uploadDir.resolve(filename);
        Files.write(filePath, file.getBytes());

        return filename; // Return only the filename
    }
}

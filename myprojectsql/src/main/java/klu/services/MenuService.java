package klu.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import klu.model.Menu;
import klu.repository.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    
    // Add a new menu item
    public Menu addMenuItem(Menu menu) {
        return menuRepository.save(menu);
    }

    // Get all menu items
    public List<Menu> getAllMenuItems() {
        return menuRepository.findAll();
    }

    // Get a menu item by ID
    public Menu getMenuItemById(Long id) {
        return menuRepository.findById(id).orElse(null);
    }

    // Delete a menu item
    public void deleteMenuItem(Long id) {
        menuRepository.deleteById(id);
    }
}

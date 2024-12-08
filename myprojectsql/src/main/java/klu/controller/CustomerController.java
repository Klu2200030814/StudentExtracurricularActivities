package klu.controller;

import klu.model.Customer;
import klu.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/students")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllStudents() {
        List<Customer> students = customerService.getAllCustomers();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Customer> addStudent(@RequestBody Customer customer) {
        Customer savedCustomer =  customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        boolean isUpdated = customerService.updateCustomer(id, updatedCustomer);
        return isUpdated
                ? new ResponseEntity<>("Student updated successfully!", HttpStatus.OK)
                : new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean isDeleted = customerService.deleteCustomer(id);
        return isDeleted
                ? new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK)
                : new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Customer customer) {
        Optional<Customer> existingCustomer = customerService.login(customer.getEmail(), customer.getPassword());
        return existingCustomer.isPresent()
                ? ResponseEntity.ok("Login successful!")
                : new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
    }
}

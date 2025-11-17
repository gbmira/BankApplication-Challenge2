package com.bankapplication.bankapplication.controller;

import com.bankapplication.bankapplication.dto.customer.CreateCustomerDTO;
import com.bankapplication.bankapplication.dto.customer.CustomerResponseDTO;
import com.bankapplication.bankapplication.dto.customer.UpdateCustomerDTO;
import com.bankapplication.bankapplication.exceptions.CustomerNotFoundException;
import com.bankapplication.bankapplication.model.Customer;
import com.bankapplication.bankapplication.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CreateCustomerDTO createCustomerDTO) {
        Customer customerCreated = customerService.createCustomer(createCustomerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerCreated);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@RequestBody UpdateCustomerDTO updateCustomerDTO, @PathVariable String cpf) {

        CustomerResponseDTO customerUpdated = customerService.updateCustomer(cpf, updateCustomerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customerUpdated);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable String cpf) {

        CustomerResponseDTO customer = customerService.getCustomer(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers() {

        List<CustomerResponseDTO> customers = customerService.getAllCustomers();

        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String cpf) {

        customerService.deleteCustomer(cpf);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.bankapplication.bankapplication.service;

import com.bankapplication.bankapplication.dto.customer.CreateCustomerDTO;
import com.bankapplication.bankapplication.dto.customer.UpdateCustomerDTO;
import com.bankapplication.bankapplication.exceptions.CustomerNotFoundException;
import com.bankapplication.bankapplication.model.Customer;
import com.bankapplication.bankapplication.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CustomerService {

    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(CreateCustomerDTO createCustomerDTO) {

        Customer customer = new Customer();

        customer.setName(createCustomerDTO.name());
        customer.setCpf(createCustomerDTO.cpf());
        customer.setPhoneNumber(createCustomerDTO.phoneNumber());
        customer.setEmail(createCustomerDTO.email());

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String cpf, UpdateCustomerDTO updateCustomerDTO) {

        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        if (updateCustomerDTO.name() != null) {
            customer.setName(updateCustomerDTO.name());
        }

        if (updateCustomerDTO.phoneNumber() != null) {
            customer.setPhoneNumber(updateCustomerDTO.phoneNumber());
        }

        if (updateCustomerDTO.email() != null) {
            customer.setEmail(updateCustomerDTO.email());
        }

        return customerRepository.save(customer);
    }

    public Customer getCustomer(String cpf) {

        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        return customer;
    }

    public List<Customer> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();

        return customers;
    }

    public void deleteCustomer(String cpf) {

        Customer customer = customerRepository.findByCpf(cpf)
                        .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        customerRepository.delete(customer);
    }
}

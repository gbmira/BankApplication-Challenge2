package com.bankapplication.bankapplication.service;

import com.bankapplication.bankapplication.dto.customer.CreateCustomerDTO;
import com.bankapplication.bankapplication.dto.customer.CustomerResponseDTO;
import com.bankapplication.bankapplication.dto.customer.UpdateCustomerDTO;
import com.bankapplication.bankapplication.exceptions.CustomerNotFoundException;
import com.bankapplication.bankapplication.mapper.customer.CustomerMapper;
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

    public CustomerResponseDTO updateCustomer(String cpf, UpdateCustomerDTO updateCustomerDTO) {

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

        return CustomerMapper.toDTO(customerRepository.save(customer));
    }

    public CustomerResponseDTO getCustomer(String cpf) {

        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        return CustomerMapper.toDTO(customer);
    }

    public List<CustomerResponseDTO> getAllCustomers() {

        List<CustomerResponseDTO> customers = customerRepository.findAll().stream().map(CustomerMapper::toDTO).toList();

        return customers;
    }

    public void deleteCustomer(String cpf) {

        Customer customer = customerRepository.findByCpf(cpf)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found!"));

        customerRepository.delete(customer);
    }
}

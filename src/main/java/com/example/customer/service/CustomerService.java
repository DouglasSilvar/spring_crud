package com.example.customer.service;

import com.example.customer.model.request.CustomerRequest;
import com.example.customer.model.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface CustomerService {

    CustomerResponse create(CustomerRequest customerRequest);

    Page<CustomerResponse> getAll(Pageable pageable);

    Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest);
    Optional<CustomerResponse> get(Long id);
    boolean delete(Long id);

}

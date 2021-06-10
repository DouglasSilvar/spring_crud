package com.example.customer.service.mapper;

import com.example.customer.model.request.CustomerRequest;
import com.example.customer.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper implements Mapper<CustomerRequest, Customer>{


    @Override
    public Customer map(CustomerRequest input) {
            if(input == null){
                return null;
            }

            Customer customer = new Customer();
            customer.setName(input.getName());
            customer.setDocument(input.getDocument());

            return customer;

    }
}

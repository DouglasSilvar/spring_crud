package com.example.customer.service.mapper;

import com.example.customer.model.response.CustomerResponse;
import com.example.customer.persistence.entity.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerResponseMapper implements Mapper<Customer, CustomerResponse>{
    @Override
    public CustomerResponse map(Customer input) {
        if(input == null){
            return null;
        }

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(input.getId());
        customerResponse.setName(input.getName());
        customerResponse.setDocument(input.getDocument());

        return customerResponse;
    }
}

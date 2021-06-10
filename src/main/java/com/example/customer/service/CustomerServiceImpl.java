package com.example.customer.service;

import com.example.customer.model.request.CustomerRequest;
import com.example.customer.model.response.CustomerResponse;
import com.example.customer.persistence.entity.Customer;
import com.example.customer.persistence.repository.CustomerRepository;
import com.example.customer.service.mapper.Mapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;

import static org.springframework.util.Assert.notNull;


@Service
public class CustomerServiceImpl implements CustomerService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Mapper<CustomerRequest, Customer> requestMapper;

    @Autowired
    private Mapper<Customer, CustomerResponse> responseMapper;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        LOGGER.info("Criando um registro do cliente");
        notNull(customerRequest, "Request invalida");
        Customer customer = this.requestMapper.map(customerRequest);
        return customerRepository.saveAndFlush(customer).map((Customer input)-> this.responseMapper.map(input));
    }

    @Override
    public Page<CustomerResponse> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");
        notNull(pageable, "Pagina invalida");
        return customerRepository.findAll(pageable).map(customer -> this.responseMapper.map(customer));

    }

    @Override
    public Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest) {
        LOGGER.info("Atualizando o registro");
        notNull(id, "ID Invalido");
        Customer customerUpdate = this.requestMapper.map(customerRequest);

        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerUpdate.getName());
            return this.responseMapper.map(customerRepository.saveAndFlush(customer));

        });
    }

    @Override
    public Optional<CustomerResponse> get(Long id) {
        LOGGER.info("Buscando o registro");
        notNull(id, "ID invalido");
        return customerRepository.findById(id).map(this.responseMapper :: map);
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Deletando o registro");
        notNull(id, "ID invalido");

        try{
            customerRepository.deleteById(id);
        }catch (Exception e){
            LOGGER.warn("Erro ao remover o registro", id);
        }
        return false;
    }
}

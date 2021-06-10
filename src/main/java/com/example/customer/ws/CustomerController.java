package com.example.customer.ws;

import com.example.customer.model.request.CustomerRequest;
import com.example.customer.model.response.CustomerResponse;
import com.example.customer.persistence.entity.Customer;
import com.example.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest){
        LOGGER.info("Requisicao recebida");
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable){
        LOGGER.info("Buscando os registros");
        Page<CustomerResponse> customerResponses = customerService.getAll(pageable);
        return ResponseEntity.ok(customerResponses);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable("id")Long id,@RequestBody CustomerRequest customerRequest){
        LOGGER.info("Iniciando a atualizacao");
        Optional<CustomerResponse> update = customerService.update(id, customerRequest);
        if(!update.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update.get());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable("id")Long id){
        LOGGER.info("Iniciando a busca pelo registros");
        Optional<CustomerResponse> customerResponse = customerService.get(id);
        if(!customerResponse.isPresent()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerResponse.get());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        LOGGER.info("Iniciando a remocao dos registros");
        if(customerService.delete(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }



}

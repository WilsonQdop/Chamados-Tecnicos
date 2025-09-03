package com.WilsonQdop.Chamadas.controllers;

import com.WilsonQdop.Chamadas.dtos.customedto.CustomerRequestDTO;
import com.WilsonQdop.Chamadas.dtos.customedto.CustomerResponseDTO;
import com.WilsonQdop.Chamadas.models.Customer;
import com.WilsonQdop.Chamadas.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("register")
    public ResponseEntity<CustomerResponseDTO> save (@RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO customer = this.customerService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Customer> find (@PathVariable UUID id) {
        Customer customer = this.customerService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<CustomerResponseDTO> find (@PathVariable UUID id, @RequestBody CustomerRequestDTO dto) {
        CustomerResponseDTO customer = this.customerService.update(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete   (@PathVariable UUID id) {
        this.customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("findAll")
    @PreAuthorize("hasAuthority('SCOPE_ADM')")
    public ResponseEntity<List<CustomerResponseDTO>> findAll   () {
       List<CustomerResponseDTO> customers = this.customerService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}

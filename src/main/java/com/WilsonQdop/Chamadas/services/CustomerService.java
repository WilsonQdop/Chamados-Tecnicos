package com.WilsonQdop.Chamadas.services;

import com.WilsonQdop.Chamadas.dtos.customedto.CustomerRequestDTO;
import com.WilsonQdop.Chamadas.dtos.customedto.CustomerResponseDTO;
import com.WilsonQdop.Chamadas.exceptions.CustomerNotFoundException;
import com.WilsonQdop.Chamadas.interfaces.CustomerInterface;
import com.WilsonQdop.Chamadas.mappers.CustomerMapper;
import com.WilsonQdop.Chamadas.models.Customer;
import com.WilsonQdop.Chamadas.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerService implements CustomerInterface {
    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerMapper mapper) {
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO dto) {

        Customer customer = mapper.toDto(dto);
        Customer save = this.customerRepository.save(customer);

        return this.mapper.toEntity(save);
    }

    @Override
    public Customer findById(UUID id) {
       return this.customerRepository.findById(id).
                orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO dto, UUID id) {
        Customer customer = this.findById(id);

           this.mapper.updateEntityFromDto(dto, customer);
           Customer update = this.customerRepository.save(customer);

        return this.mapper.toEntity(update);
    }

    @Override
    public void delete(UUID id) {
        Customer customer = this.findById(id);

        this.customerRepository.delete(customer);
    }
}

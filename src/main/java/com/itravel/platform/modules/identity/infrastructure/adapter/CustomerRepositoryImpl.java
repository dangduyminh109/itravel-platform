package com.itravel.platform.modules.identity.infrastructure.adapter;

import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.domain.customer.CustomerId;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.CustomerMapper;
import com.itravel.platform.modules.identity.infrastructure.persistence.repository.CustomerJpaRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerRepositoryImpl implements CustomerRepository {
    CustomerJpaRepository customerJpaRepository;
    CustomerMapper mapper;

    @Override
    public Optional<Customer> findById(CustomerId id) {
        return customerJpaRepository
                .findById(id.value()).map(CustomerMapper::toCustomerDomain);
    }

    @Override
    public void save(Customer customer) {
        CustomerJpaEntity customerJpaEntity = mapper.toCustomerJpaEntity(customer);
        customerJpaRepository.save(customerJpaEntity);
    }

    @Override
    public void destroy(CustomerId id) {
        customerJpaRepository.findById(id.value())
                .ifPresent(customerJpaRepository::delete);
    }
}

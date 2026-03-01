package com.itravel.platform.modules.identity.infrastructure.persistence.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import com.itravel.platform.modules.identity.domain.repository.CustomerRepository;
import com.itravel.platform.modules.identity.infrastructure.persistence.entity.CustomerJpaEntity;
import com.itravel.platform.modules.identity.infrastructure.persistence.mapper.CustomerMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CustomerRepositoryImpl implements CustomerRepository {
    CustomerJpaRepository customerJpaRepository;
    CustomerMapper mapper;

    @Override
    public Optional<Customer> findById(CustomerId id) {
        return customerJpaRepository
                .findById(id.value()).map(CustomerMapper::toCustomerDomain);
    }

    @Override
    public Page<Customer> getCustomers(String keyword, Pageable pageable) {
        return customerJpaRepository
                .searchCustomer(keyword, pageable)
                .map(CustomerMapper::toCustomerDomain);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerJpaRepository
                .findAll()
                .stream()
                .map(CustomerMapper::toCustomerDomain)
                .toList();
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

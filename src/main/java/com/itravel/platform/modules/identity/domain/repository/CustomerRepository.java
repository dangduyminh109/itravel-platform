package com.itravel.platform.modules.identity.domain.repository;

import com.itravel.platform.modules.identity.domain.aggregate.Customer;
import com.itravel.platform.modules.identity.domain.aggregate.valueobject.CustomerId;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository {
    Optional<Customer> findById(CustomerId id);
    List<Customer> getCustomers();
    void save(Customer customer);
    void destroy(CustomerId id);
}

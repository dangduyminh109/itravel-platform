package com.itravel.platform.modules.identity.application.port.out.customer;

import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.domain.customer.CustomerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository {
    Optional<Customer> findById(CustomerId id);
    void save(Customer customer);
    void destroy(CustomerId id);
}


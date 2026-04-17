package com.itravel.platform.modules.identity.application.command.service.auth;

import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByGoogleCommand;
import com.itravel.platform.modules.identity.application.command.model.customer.RegisterCustomerByGoogleCommand;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByGoogleUseCase;
import com.itravel.platform.modules.identity.application.port.in.auth.RegisterCustomerByGoogleUseCase;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import com.itravel.platform.modules.identity.domain.account.Account;
import com.itravel.platform.modules.identity.domain.customer.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegisterCustomerByGoogleService implements RegisterCustomerByGoogleUseCase {
    CustomerRepository customerRepository;
    CreateAccountByGoogleUseCase createAccountByGoogleUseCase;

    @Override
    @Transactional
    public Account execute(RegisterCustomerByGoogleCommand command) {
        Customer customer = Customer.create(
                command.fullName(),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );

        CreateAccountByGoogleCommand createAccountByGoogleCommand =
                new CreateAccountByGoogleCommand(
                        command.email(),
                        customer.getId(),
                        command.providerId()
                );
        Account account = createAccountByGoogleUseCase.execute(createAccountByGoogleCommand);
        customerRepository.save(customer);
        return account;
    }
}

package com.itravel.platform.modules.identity.application.command.service.customer;
import com.itravel.platform.modules.identity.application.command.model.customer.*;
import com.itravel.platform.modules.identity.application.exception.CustomerNotExistException;
import com.itravel.platform.modules.identity.domain.customer.Customer;
import com.itravel.platform.modules.identity.application.port.out.customer.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.customer.DeleteCustomerUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;
import com.itravel.platform.modules.identity.application.command.model.account.DeleteAccountCommand;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeleteCustomerService implements DeleteCustomerUseCase {
    CustomerRepository customerRepository;
    AccountCommandFacade accountCommandHandler;

    @Transactional
    public void execute(DeleteCustomerCommand command) {
        Customer customer = customerRepository.findById(command.id())
                .orElseThrow(CustomerNotExistException::new);
        accountCommandHandler.delete(new DeleteAccountCommand(command.id().value()));
        customer.softDelete();
        customerRepository.save(customer);
    }
}
    


package com.itravel.platform.modules.identity.application.command.service.user;
import com.itravel.platform.modules.identity.application.command.model.account.DeleteAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.user.*;
import com.itravel.platform.modules.identity.application.port.out.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;
import com.itravel.platform.modules.identity.application.port.in.user.DestroyUserUseCase;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DestroyUserService implements DestroyUserUseCase {
    UserRepository userRepository;
    AccountCommandFacade accountCommandHandler;
   
    @Transactional
    public void execute(DeleteUserCommand command){
        userRepository.destroy(command.id());
        accountCommandHandler.destroy(new DeleteAccountCommand(command.id().value()));
    }
}


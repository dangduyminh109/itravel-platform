package com.itravel.platform.modules.identity.application.command.service.user;
import com.itravel.platform.modules.identity.application.command.model.account.RestoreAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.user.*;
import com.itravel.platform.modules.identity.application.exception.UserNotExistException;
import com.itravel.platform.modules.identity.domain.user.User;
import com.itravel.platform.modules.identity.application.port.out.user.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itravel.platform.modules.identity.application.port.in.account.facade.AccountCommandFacade;
import com.itravel.platform.modules.identity.application.port.in.user.RestoreUserUseCase;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RestoreUserService implements RestoreUserUseCase {
    UserRepository userRepository;
    AccountCommandFacade accountCommandHandler;
    
    @Transactional
    public void execute(RestoreUserCommand command){
        User user = userRepository.findById(command.id())
                .orElseThrow(UserNotExistException::new);
        accountCommandHandler.restore(new RestoreAccountCommand(command.id().value()));
        user.restore();
        userRepository.save(user);
    }
}


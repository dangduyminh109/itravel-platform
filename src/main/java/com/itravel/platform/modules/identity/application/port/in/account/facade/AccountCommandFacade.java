package com.itravel.platform.modules.identity.application.port.in.account.facade;
import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByEmailCommand;
import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByGoogleCommand;
import com.itravel.platform.modules.identity.application.command.model.account.CreateAccountByUserNameCommand;
import com.itravel.platform.modules.identity.application.command.model.account.DeleteAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.account.RestoreAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountCommand;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountPasswordCommand;
import com.itravel.platform.modules.identity.application.command.model.account.UpdateAccountStatusCommand;
import com.itravel.platform.modules.identity.application.port.in.account.ChangePasswordUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.ChangeStatusUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByEmailUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByGoogleUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.CreateAccountByUserNameUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.DeleteAccountUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.DestroyAccountUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.RestoreAccountUseCase;
import com.itravel.platform.modules.identity.application.port.in.account.UpdateAccountUseCase;
import com.itravel.platform.modules.identity.domain.account.Account;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountCommandFacade {
    CreateAccountByUserNameUseCase createAccountByUserNameUseCase;
    CreateAccountByEmailUseCase createAccountByEmailUseCase;
    CreateAccountByGoogleUseCase createAccountByGoogleUseCase;
    ChangePasswordUseCase changePasswordUseCase;
    ChangeStatusUseCase changeStatusUseCase;
    UpdateAccountUseCase updateAccountUseCase;
    DeleteAccountUseCase deleteAccountUseCase;
    RestoreAccountUseCase restoreAccountUseCase;
    DestroyAccountUseCase destroyAccountUseCase;
    public Account createByUserName(CreateAccountByUserNameCommand command) { return createAccountByUserNameUseCase.execute(command); }
    public Account createByEmail(CreateAccountByEmailCommand command) { return createAccountByEmailUseCase.execute(command); }
    public Account createByGoogle(CreateAccountByGoogleCommand command) { return createAccountByGoogleUseCase.execute(command); }
    public void changePassword(UpdateAccountPasswordCommand command) { changePasswordUseCase.execute(command); }
    public Account changeStatus(UpdateAccountStatusCommand command) { return changeStatusUseCase.execute(command); }
    public Account update(UpdateAccountCommand command) { return updateAccountUseCase.execute(command); }
    public void delete(DeleteAccountCommand command) { deleteAccountUseCase.execute(command); }
    public void restore(RestoreAccountCommand command) { restoreAccountUseCase.execute(command); }
    public void destroy(DeleteAccountCommand command) { destroyAccountUseCase.execute(command); }
}

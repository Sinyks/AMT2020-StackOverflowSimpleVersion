package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.domain.exceptions.DataCorruptionException;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;

public class AuthenticationManagementFacade {
    private IUserRepository personRepository;
    public AuthenticationManagementFacade(IUserRepository personRepository){
        this.personRepository=personRepository;
    }

    public CurrentUserDTO register (RegisterCommand command) throws RegisterFailedException, DataCorruptionException {
        User homonyme = personRepository.retrieveByUsername(command.getUsername()).orElse(null);

        if(homonyme != null){
            throw new RegisterFailedException("Username already in use");
        }

        if(!command.getClearTextPassword().equals(command.getClearTextPasswordConfirm())){
            throw new RegisterFailedException("Passwords don't match");
        }

        try{
            User newUser = User.builder()
                    .username(command.getUsername())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearTextPassword())
                    .build();
            personRepository.create(newUser);

            return createCurrentUserDTO(newUser.getUsername());

        } catch (Exception e){
            throw new RegisterFailedException(e.getMessage());
        }

    }

    public CurrentUserDTO login(LoginCommand command) throws LoginFailedException, DataCorruptionException {
        User user = personRepository.retrieveByUsername(command.getUsername()).orElse(null);
        if(user == null){
            throw new LoginFailedException("User not found");
        }


    boolean success = user.login(command.getClearTextPassword());

    if(!success){
        throw new LoginFailedException("Credentials verification failed");
    }

    return createCurrentUserDTO(user.getUsername());
    }

    private CurrentUserDTO createCurrentUserDTO(String username){
        CurrentUserDTO currentUser = CurrentUserDTO.builder()
                .username(username)
                .build();

        return currentUser;
    }
}

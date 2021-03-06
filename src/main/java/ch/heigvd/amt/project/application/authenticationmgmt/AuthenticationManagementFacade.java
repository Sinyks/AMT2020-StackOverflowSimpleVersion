package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;

public class AuthenticationManagementFacade {
    private IUserRepository personRepository;

    public AuthenticationManagementFacade(IUserRepository personRepository) {
        this.personRepository = personRepository;
    }

    public CurrentUserDTO register(RegisterCommand command) throws RegisterFailedException {
        User homonyme = personRepository.findByUsername(command.getUsername()).orElse(null);

        if (homonyme != null) {
            throw new RegisterFailedException("Username already in use");
        }

        if (!command.getClearTextPassword().equals(command.getClearTextPasswordConfirm())) {
            throw new RegisterFailedException("Passwords don't match");
        }

        try {
            User newUser = User.builder()
                    .username(command.getUsername())
                    .email(command.getEmail())
                    .clearTextPassword(command.getClearTextPassword())
                    .build();
            personRepository.save(newUser);

            return CurrentUserDTO.builder()
                    .id(newUser.getId())
                    .username((newUser.getUsername()))
                    .email(newUser.getEmail())
                    .aboutMe(newUser.getAboutMe())
                    .build();

        } catch (Exception e) {
            throw new RegisterFailedException(e.getMessage());
        }

    }

    public int getUserCount(){
        return personRepository.findAll().size();
    }

    public CurrentUserDTO login(LoginCommand command) throws LoginFailedException {
        User user = personRepository.findByUsername(command.getUsername()).orElse(null);
        if (user == null) {
            throw new LoginFailedException("User not found"); // is this message a security failure ?
        }


        boolean success = user.login(command.getClearTextPassword());

        if (!success) {
            throw new LoginFailedException("Credentials verification failed");
        }

        return CurrentUserDTO.builder()
                .id(user.getId())
                .username((user.getUsername()))
                .email(user.getEmail())
                .aboutMe(user.getAboutMe())
                .build();
    }
}

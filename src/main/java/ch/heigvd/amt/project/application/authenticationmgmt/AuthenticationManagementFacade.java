package ch.heigvd.amt.project.application.authenticationmgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.login.LoginFailedException;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterCommand;
import ch.heigvd.amt.project.application.authenticationmgmt.register.RegisterFailedException;
import ch.heigvd.amt.project.domain.person.IPersonRepository;
import ch.heigvd.amt.project.domain.person.Person;

public class AuthenticationManagementFacade {
    private IPersonRepository personRepository;
    public AuthenticationManagementFacade(IPersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public void register (RegisterCommand command) throws RegisterFailedException{
        Person homonyme = personRepository.findByUsername(command.getUsername()).orElse(null);

        if(homonyme != null){
            throw new RegisterFailedException("Username already in use");
        }

        if(!command.getClearTextPassword().equals(command.getClearTextPasswordConfirm())){
            throw new RegisterFailedException("Passwords don't match");
        }

        try{
            Person newPerson = Person.builder()
                    .username(command.getUsername())
                    .clearTextPassword(command.getClearTextPassword())
                    .build();
            personRepository.save(newPerson);
        } catch (Exception e){
            throw new RegisterFailedException(e.getMessage());
        }

    }

    public CurrentUserDTO login(LoginCommand command) throws LoginFailedException{
        Person person = personRepository.findByUsername(command.getUsername())
                .orElseThrow() -> new LoginFailedException("User not found");

    boolean success = person.login(command.getClearTextPassword());

    if(!success){
        throw new LoginFailedException("Credentials verification failed");
    }

    CurrentUserDTO currentUser = CurrentUserDTO.builder()
            .username(person.getUsername())
            .build();

    return currentUser;
    }
}

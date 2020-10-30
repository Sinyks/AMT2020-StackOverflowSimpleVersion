package ch.heigvd.amt.project.application.profilemgmt;

import ch.heigvd.amt.project.application.authenticationmgmt.CurrentUserDTO;
import ch.heigvd.amt.project.application.profilemgmt.info.ProfileInfoCommand;
import ch.heigvd.amt.project.application.profilemgmt.info.ProfileInfoFailedException;
import ch.heigvd.amt.project.application.profilemgmt.password.ProfilePasswordCommand;
import ch.heigvd.amt.project.application.profilemgmt.password.ProfilePasswordFailedException;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;

public class ProfileManagementFacade {

    private IUserRepository personRepository;

    public ProfileManagementFacade(IUserRepository personRepository) {
        this.personRepository = personRepository;
    }

    public CurrentUserDTO updateInfo(ProfileInfoCommand command) throws ProfileInfoFailedException {
        User userToEdit = personRepository.findById(command.getId()).orElse(null);

        if (userToEdit == null){
            throw new ProfileInfoFailedException("user doesn't exist");
        }

        try{
        userToEdit.updatePersonalInformations(command.getNewUsername(),
                command.getNewAboutMe(),
                command.getNewEmail());

            personRepository.updateById(userToEdit.getId(), userToEdit.getUsername(), userToEdit.getAboutMe(), userToEdit.getEmail(), userToEdit.getHashedPassword());

            return CurrentUserDTO.builder()
                    .id(userToEdit.getId())
                    .username((userToEdit.getUsername()))
                    .email(userToEdit.getEmail())
                    .aboutMe(userToEdit.getAboutMe())
                    .build();

        } catch (IllegalArgumentException e){
            throw new ProfileInfoFailedException("Command for user "+userToEdit.getUsername()+" is empty");
        }


    }

    public void updatePassword(ProfilePasswordCommand command) throws ProfilePasswordFailedException {
        User userToEdit = personRepository.findById(command.getId()).orElse(null);

        if (userToEdit == null){
            throw new ProfilePasswordFailedException("user doesn't exist");
        }

        if(!userToEdit.login(command.getCurrentClearPassword())){
            throw new ProfilePasswordFailedException("Wrong old password");
        }

        if(!command.getNewClearTextPassword().equals(command.getNewClearTextPasswordConfirm())){
            throw new ProfilePasswordFailedException("Pasword mismatch");
        }


        try{

            userToEdit.updatePassword(command.getNewClearTextPassword());

            personRepository.updateById(userToEdit.getId(), userToEdit.getUsername(), userToEdit.getAboutMe(), userToEdit.getEmail(), userToEdit.getHashedPassword());

        } catch (IllegalArgumentException e){
            throw new ProfilePasswordFailedException("Command for user "+userToEdit.getUsername()+" is empty");
        }
    }
}

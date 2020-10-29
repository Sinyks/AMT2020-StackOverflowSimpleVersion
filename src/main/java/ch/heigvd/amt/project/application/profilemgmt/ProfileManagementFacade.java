/* package ch.heigvd.amt.project.application.profilemgmt;

import ch.heigvd.amt.project.application.profilemgmt.info.ProfileInfoCommand;
import ch.heigvd.amt.project.application.profilemgmt.info.ProfileInfoFailedException;
import ch.heigvd.amt.project.domain.user.IUserRepository;
import ch.heigvd.amt.project.domain.user.User;

public class ProfileManagementFacade {

    private IUserRepository personRepository;

    public ProfileManagementFacade(IUserRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void updateInfo(ProfileInfoCommand profileInfoCommand) throws ProfileInfoFailedException {
        User userToEdit = personRepository.findById(profileInfoCommand.getId()).orElse(null);

        if (userToEdit == null){
            throw new ProfileInfoFailedException("user doesn't exist");
        }

        try{
        userToEdit.updatePersonalInformations(profileInfoCommand.getNewUsername(),
                profileInfoCommand.getNewAboutMe(),
                profileInfoCommand.getNewEmail());
        } catch (IllegalArgumentException e){
            throw new ProfileInfoFailedException("Command for user "+userToEdit.getUsername()+" is empty");
        }

        personRepository.updateById(userToEdit.getId(), userToEdit.getUsername(), userToEdit.getAboutMe(), userToEdit.getEmail(), userToEdit.getHashedPassword());
    }
}*/

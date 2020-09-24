package ch.heigvd.amt.project.application;

import jdk.nashorn.internal.objects.annotations.Getter;

@Builder // lié a lambok
@Getter
public class RegisterCommand {
    private String username;
    private String password;
    private String confirmePassword;
}

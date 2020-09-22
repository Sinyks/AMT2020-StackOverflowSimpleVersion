package ch.heigvd.amt.project.application;

import jdk.nashorn.internal.objects.annotations.Getter;

@Builder // lié a lambok
@Getter
public class LoginCommand {
    private String username;
    private String password;
}

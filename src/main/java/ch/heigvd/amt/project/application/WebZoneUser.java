package ch.heigvd.amt.project.application;

import javax.imageio.spi.ServiceRegistry;
import java.util.ArrayList;

public class WebZoneUser {
    public static ArrayList<WebZoneUser> fakeDatabase = new ArrayList<WebZoneUser>();
    private String username;
    private String password;

    public WebZoneUser(String username, String password){
        this.username=username;//verifier s'il est pas déjà pris
        this.password=password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}

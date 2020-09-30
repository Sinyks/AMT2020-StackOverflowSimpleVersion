package ch.heigvd.amt.project.infrastructure;

import javax.imageio.spi.ServiceRegistry;
import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;

public class FakeDataBase {
    private static HashMap<String,String> fakeDatabase = new HashMap<>();

    static public void isAuth(String username, String password){
        if(!isCorrectPassword(username,password)){
            throw new IllegalArgumentException("incorrect password");
        }
    }

    static private boolean isCorrectPassword(String username, String password){
        if(isInDataBase(username)) {
            String tmp = fakeDatabase.get(username);
            return fakeDatabase.get(username).equals(password);
        }
        return false;
    }

    static private boolean isInDataBase(String username){
        return fakeDatabase.containsKey(username);
    }

    static public void addToDataBase(String username, String password){
        if(isInDataBase(username)){
            throw new IllegalArgumentException("already in database");
        }
        fakeDatabase.put(username,password);
    }
}

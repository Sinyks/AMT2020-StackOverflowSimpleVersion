package ch.heigvd.amt.project.dotenv;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class DotenvManager {
    private static String ENV_PATH = "src/main/liberty/config";

    public static Dotenv getDotenv(){

        return Dotenv.configure()
                .directory(ENV_PATH)
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .filename("server.env")
                .load();
    }
}

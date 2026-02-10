package Config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.UUID;

public class Hashed {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String cont) {
        return encoder.encode(cont);
    }

    public static String generarPasswordTemporal() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}

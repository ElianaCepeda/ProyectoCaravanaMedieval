package co.edu.javeriana.juego_caravana_medieval.model;
import org.springframework.security.core.GrantedAuthority;

// Type safe roles for Spring Security
// https://stackoverflow.com/a/54713712

public enum Role implements GrantedAuthority {
    COMERCIANTE(Code.COMERCIANTE),
    CARAVANERO(Code.CARAVANERO);

    private final String authority;

    Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public class Code {
        public static final String COMERCIANTE = "COMERCIANTE";
        public static final String CARAVANERO = "CARAVANERO";
    }
}

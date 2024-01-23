package sarahguarneri.BEU2W3L2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.BEU2W3L2.entities.User;
import sarahguarneri.BEU2W3L2.payload.UserLoginDTO;
import io.jsonwebtoken.Jwts;
import sarahguarneri.BEU2W3L2.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticatedUser(UserLoginDTO body){
        // 1. verifica che email sia nel db
        User user = userService.findByEmail(body.email());

        // 2. se sì --> verifica che password corrisponda a quella nel db
        if(body.password().equals(user.getPassword())){
        // 3. se passw ok --> generare token JWT e tornarlo
            return jwtTools.createToken(user);
        } else {
        // 4. se pssw non ok --> 401 (UNAUTHORIZED)
            throw new RuntimeException("Credenziali non valide");
        }
    }
}

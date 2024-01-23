package sarahguarneri.BEU2W3L2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sarahguarneri.BEU2W3L2.payload.UserLoginDTO;
import sarahguarneri.BEU2W3L2.payload.UserLoginResponseDTO;
import sarahguarneri.BEU2W3L2.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthoritazionController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body){
        String accessToken = authService.authenticatedUser(body);
        return new UserLoginResponseDTO(accessToken);
    }
}

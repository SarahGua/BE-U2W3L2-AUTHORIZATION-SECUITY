package sarahguarneri.BEU2W3L2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.BEU2W3L2.dao.UserDAO;
import sarahguarneri.BEU2W3L2.entities.User;
import sarahguarneri.BEU2W3L2.payload.NewUserDTO;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User findByEmail(String email){
        return userDAO.findByEmails(email).orElseThrow(() -> new RuntimeException("Utente con email " + email + " non trovata"));
    }



}

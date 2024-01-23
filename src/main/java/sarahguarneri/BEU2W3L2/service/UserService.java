package sarahguarneri.BEU2W3L2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sarahguarneri.BEU2W3L2.dao.UserDAO;
import sarahguarneri.BEU2W3L2.entities.Role;
import sarahguarneri.BEU2W3L2.entities.User;
import sarahguarneri.BEU2W3L2.payload.NewUserDTO;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public User findByEmail(String email){
        return userDAO.findByEmails(email).orElseThrow(() -> new RuntimeException("Utente con email " + email + " non trovata"));
    }

    public List<User> getUsers(){
        return userDAO.findAll();
    }

    public User findById(UUID id) {
        return userDAO.findById(id).orElseThrow(() -> new RuntimeException("erroreeee"));
    }

    public User save(NewUserDTO body){
        userDAO.findByEmails(body.email()).ifPresent(user -> {throw new RuntimeException("errore");});
        User newUser = new User();
        newUser.setEmail(body.email());
        newUser.setRole(Role.ADMIN);
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setPassword(body.password());
        return userDAO.save(newUser);
    }
}

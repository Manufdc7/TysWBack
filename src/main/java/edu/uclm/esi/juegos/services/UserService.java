package edu.uclm.esi.juegos.services;

import edu.uclm.esi.juegos.entities.User;
import edu.uclm.esi.juegos.exhandling.UserAlreadyExistsException;

//import edu.uclm.esi.juegos.exceptions.UserAlreadyExistsException;
import java.util.List;

public interface UserService {
    User registerUser(String email, String username, String password)throws UserAlreadyExistsException;
    User findUserByEmail(String email);
    User findUserById(Integer id);
    List<User> getAllUsers();
}

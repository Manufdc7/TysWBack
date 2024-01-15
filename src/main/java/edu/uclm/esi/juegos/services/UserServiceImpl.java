package edu.uclm.esi.juegos.services;

import edu.uclm.esi.juegos.entities.User;
import edu.uclm.esi.juegos.exhandling.UserAlreadyExistsException;
import edu.uclm.esi.juegos.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public User registerUser(String email, String username, String password) throws UserAlreadyExistsException {
        if(userDAO.findByEmail(email) != null) {
            throw new UserAlreadyExistsException("There is already a user registered with the email provided.");
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUser_name(username);
        newUser.setPwd(password); // Assumes hashing is done within setPwd method
        return userDAO.save(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }
    
    public User findUserById(Integer id) {
        return userDAO.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }
}

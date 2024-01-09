package edu.uclm.esi.juegos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.uclm.esi.juegos.entities.User;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

}

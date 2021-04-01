package eci.ieti.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import eci.ieti.data.model.User;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {

    List<User> findByName(String name);

    User findByEmail(String email);
    
}

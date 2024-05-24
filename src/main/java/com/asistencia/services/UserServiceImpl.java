package com.asistencia.services;

import com.asistencia.models.User;
import com.asistencia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        // Lógica para crear un nuevo usuario
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        // Lógica para obtener todos los usuarios
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        // Lógica para obtener un usuario por su ID
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(Long id, User user) {
        // Lógica para actualizar un usuario existente
        if (userRepository.existsById(id)) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        // Lógica para eliminar un usuario por su ID
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

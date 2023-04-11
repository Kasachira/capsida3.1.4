package ru.alishev.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.model.Role;
import ru.alishev.springcourse.model.User;
import ru.alishev.springcourse.repos.RoleRepository;
import ru.alishev.springcourse.repos.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User show(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    @Override
    public Role showRole(int id) {
        Optional<Role> optionalUser = roleRepository.findById(id);

        return optionalUser.get();
    }

    @Override
    public void update(User user, String[] role) {
        Set<Role> rol = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rol.add(showRole(1));
            } else {
                rol.add(showRole(2));
            }
        }
        user.setRoles(rol);
        userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}

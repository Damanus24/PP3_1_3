package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.UserUserDetailsImpl;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
//@EnableTransactionManagement
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
//    private final EntityManager entityManager;

    @Autowired
    public UserDetailsServiceImpl(UserRepository useRepository/*, EntityManager entityManager*/) {
        this.userRepository = useRepository;
//        this.entityManager = entityManager;
    }

//    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new UserUserDetailsImpl(user.get());
    }

//    @PostConstruct
//    @Transactional
//    public void postConstruct() {
//        Role adminRole = new Role("ROLE_ADMIN");
//        Role userRole = new Role("ROLE_USER");
//        List<Role> adminRolesList = List.of(adminRole, userRole);
//        List<Role> userRolesList = List.of(userRole);
//
//        User admin = new User("test", "test", 99, "admintest@mail.ru", "23",
//                adminRolesList);
//
//        entityManager.merge(admin);
//        userRepository.save(admin);
//    }

}

package com.terricabrel.authapi.bootstrap;

import com.terricabrel.authapi.dtos.RegisterUserDto;
import com.terricabrel.authapi.entities.Role;
import com.terricabrel.authapi.entities.User;
import com.terricabrel.authapi.entities.enums.RoleEnum;
import com.terricabrel.authapi.repositories.RoleRepository;
import com.terricabrel.authapi.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminSeeder(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadSuperAdministrators();
    }

    private void loadSuperAdministrators() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFullName("Super Admin");
        userDto.setEmail("super.admin@email.com");
        userDto.setPassword(passwordEncoder.encode("admin123"));

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            // TODO: manca fallback
            return;
        }

        User superAdmin = new User();
        superAdmin.setFullName(userDto.getFullName());
        superAdmin.setEmail(userDto.getEmail());
        superAdmin.setPassword(userDto.getPassword());
        superAdmin.setRole(optionalRole.get());

        userRepository.save(superAdmin);
    }
}

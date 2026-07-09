package com.uca.pncparcialfinalrestaurante.role;

import com.uca.pncparcialfinalrestaurante.role.entity.Role;
import com.uca.pncparcialfinalrestaurante.role.entity.UserRole;
import com.uca.pncparcialfinalrestaurante.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            for (UserRole userRole : UserRole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(userRole)
                                .build()
                );
            }
            System.out.println("Roles successfully seeded in the database: CLIENT, ADMIN, RESTAURANT");
        }
    }
}

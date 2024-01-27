package com.company.botadminpanel.config.init;

import com.company.botadminpanel.enums.RoleEnum;
import com.company.botadminpanel.model.Admin;
import com.company.botadminpanel.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class LoadDatabase {

    private final AdminRepository adminRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {

            Admin admin = Admin.builder()
                    .firstName("Admin")
                    .lastName("Adminov")
                    .password(new BCryptPasswordEncoder().encode("123"))
                    .phoneNumber("+998904430302")
                    .roleEnum(RoleEnum.SUPER_ADMIN)
                    .username("ADMIN")
                    .build();
            if (adminRepository.findByUsername("ADMIN").isEmpty())
                adminRepository.save(admin);
        };
    }
}

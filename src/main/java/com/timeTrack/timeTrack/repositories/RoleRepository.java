package com.timeTrack.timeTrack.repositories;


import com.timeTrack.timeTrack.models.auth.ERole;
import com.timeTrack.timeTrack.models.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

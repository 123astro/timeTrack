package com.timeTrack.timeTrack.repositories;

import com.timeTrack.timeTrack.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository <AppUser,Long> {

}

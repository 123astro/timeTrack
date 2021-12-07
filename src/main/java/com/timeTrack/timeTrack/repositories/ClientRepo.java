package com.timeTrack.timeTrack.repositories;

import com.timeTrack.timeTrack.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClientRepo extends JpaRepository <Client, Long> {

}

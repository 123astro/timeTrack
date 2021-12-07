package com.timeTrack.timeTrack.repositories;

import com.timeTrack.timeTrack.models.Matter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MatterRepo extends JpaRepository <Matter, Long> {

}

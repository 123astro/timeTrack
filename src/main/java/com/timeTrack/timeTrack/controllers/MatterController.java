package com.timeTrack.timeTrack.controllers;

import com.timeTrack.timeTrack.TimeTracker;
import com.timeTrack.timeTrack.models.Matter;
import com.timeTrack.timeTrack.repositories.MatterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/case")
public class MatterController {

    public TimeTracker timeTracker;

    @Autowired
    private MatterRepo matterRepo;

    @GetMapping
    public List<Matter> getMatters() {
        return matterRepo.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Matter getCase(@PathVariable Long id) {
        return matterRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Matter createMatter(@RequestBody Matter matter) {
        return matterRepo.save(matter);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateMatter(@RequestBody Matter updates, @PathVariable Long id) {
        Matter matter = matterRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updates.getTitle() != null) matter.setTitle(updates.getTitle());
        return new ResponseEntity<>(matterRepo.save(updates), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteMatter(@PathVariable Long id) {
        matterRepo.deleteById(id);
    }

    //timer

    @PostMapping("/startTime")
    public LocalDateTime startTime() {
        timeTracker = new TimeTracker();
        return timeTracker.getStartTime();
    }

    @PostMapping("/endTime")
    public LocalDateTime endTime() {
        timeTracker.setEndTime();
        return timeTracker.getEndTime();
    }

    @GetMapping("/duration")
    public Duration duration() {
        return timeTracker.getTotalTime();
    }

    @PutMapping("/duration/{id}")
    public Matter addDuration(@PathVariable Long id) {
        Matter matter = matterRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        matter.setDuration(timeTracker.getTotalTime().getSeconds());
        return matter;
    }


}

package com.timeTrack.timeTrack.controllers;

import com.timeTrack.timeTrack.models.Client;
import com.timeTrack.timeTrack.models.Matter;
import com.timeTrack.timeTrack.repositories.ClientRepo;
import com.timeTrack.timeTrack.repositories.MatterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")

public class ClientController {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private MatterRepo matterRepo;

    @GetMapping
    public List<Client> getClients() {
        return clientRepo.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Client getClient(@PathVariable Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientRepo.save(client);
    }

    @PutMapping("/case/{clientId}")
    public ResponseEntity<?> addCases(@PathVariable Long clientId, @RequestBody List<Matter> matters){
        Client client = clientRepo.findById(clientId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        for(Matter matter : matters){
            client.getMatter().add(matter);
            Optional<Matter> mat = matterRepo.findById(matter.getId());
            if(mat.isEmpty())
                continue;
            mat.get().setClient(client);
            matterRepo.save(mat.get());
        }
        return new ResponseEntity<>(clientRepo.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateClient(@RequestBody Client updates, @PathVariable Long id ) {
        Client client = clientRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (updates.getName() != null) client.setName(updates.getName());
        if (updates.getEmail() != null) client.setEmail(updates.getEmail());
        if (updates.getRole() != null) client.setRole(updates.getRole());
   //     if (updates.getMatter() != null) client.setMatter(updates.getMatter());
        return new ResponseEntity<>(clientRepo.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientRepo.deleteById(id);
    }
}

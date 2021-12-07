package com.timeTrack.timeTrack.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity

public class Matter {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long durationInSec;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnoreProperties("matter")
    private Client client;


    public Matter(){};

    public Matter(String title, Long duration,  Client client){
        this.title = title;
        this.durationInSec = duration;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getDuration() {
        return durationInSec;
    }

    public void setDuration(Long duration) {
        this.durationInSec = duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

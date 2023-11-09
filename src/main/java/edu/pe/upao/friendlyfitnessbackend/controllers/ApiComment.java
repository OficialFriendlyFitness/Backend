package edu.pe.upao.friendlyfitnessbackend.controllers;

import edu.pe.upao.friendlyfitnessbackend.models.Client;

public class ApiComment {
    private String comment;
    private Client client;

    public ApiComment(String comment, Client client) {
        this.comment = comment;
        this.client = client;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Client getUser() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
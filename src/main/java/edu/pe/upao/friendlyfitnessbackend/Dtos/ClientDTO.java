package edu.pe.upao.friendlyfitnessbackend.Dtos;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import lombok.Getter;

@Getter
public class ClientDTO {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String cell;
    private String tastes;
    private String expectations;
    private String preferences;

    public ClientDTO(Client client) {
        this.email = client.getEmail();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.age = client.getAge();
        this.cell = client.getCell();
        this.expectations = client.getExpectations();
        this.tastes = client.getTastes();
        this.preferences = client.getPreferences();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setTastes(String tastes) {
        this.tastes = tastes;
    }

    public void setExpectations(String expectations) {
        this.expectations = expectations;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
    public ClientDTO(String email) {
        this.email = email;
    }
}

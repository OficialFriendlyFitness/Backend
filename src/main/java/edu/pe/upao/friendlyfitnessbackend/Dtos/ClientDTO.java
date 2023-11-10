package edu.pe.upao.friendlyfitnessbackend.Dtos;

import edu.pe.upao.friendlyfitnessbackend.models.Client;
import lombok.Getter;

@Getter
public class ClientDTO {
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public ClientDTO(Client client) {
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.age = client.getAge();
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
}

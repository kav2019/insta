package ru.kovshov.insta.payload.responce;

import lombok.Getter;

@Getter
public class InvalidLoginResponce {

    private String name;
    private String password;

    public InvalidLoginResponce() {
        this.name = "Invalid Username";
        this.password = "Invalid Password";
    }
}

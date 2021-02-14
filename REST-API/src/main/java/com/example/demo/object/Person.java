package com.example.demo.object;

public class Person {

    private String emailAddress;
    private String name;
    private String location;

    public Person()
    {
        super();
    }


    public Person(String emailAddress, String name, String location) {
        super();
        this.emailAddress = emailAddress;
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

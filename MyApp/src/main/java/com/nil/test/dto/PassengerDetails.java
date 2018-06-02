
package com.nil.test.dto;

public class PassengerDetails {

    private String name;
    private String phoneNumber;
    private String email;
    private String photoUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\"," +
                "\"phoneNumber\":\"" + phoneNumber + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"photoUrl\":\"" + photoUrl + "\"}";
    }
}

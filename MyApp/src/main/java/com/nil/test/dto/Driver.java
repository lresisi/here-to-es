
package com.nil.test.dto;

public class Driver {

    private String drivingLicenseId;
    private String name;
    private String phoneNumber;
    private String photoUrl;

    public String getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(String drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "{\"drivingLicenseId\":\"" + drivingLicenseId + "\"," +
                "\"name\":\"" + name + "\"," +
                "\"phoneNumber\":\"" + phoneNumber + "\"," +
                "\"photoUrl\":\"" + photoUrl + "\"}";
    }
}

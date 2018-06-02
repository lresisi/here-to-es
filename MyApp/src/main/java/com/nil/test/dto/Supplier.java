
package com.nil.test.dto;

public class Supplier {

    private String getAddress;
    private String getEnglishName;
    private String getLocalName;
    private String getLogoUrl;
    private String getPhoneNumber;
    private String supplierId;

    public String getGetAddress() {
        return getAddress;
    }

    public void setGetAddress(String getAddress) {
        this.getAddress = getAddress;
    }

    public String getGetEnglishName() {
        return getEnglishName;
    }

    public void setGetEnglishName(String getEnglishName) {
        this.getEnglishName = getEnglishName;
    }

    public String getGetLocalName() {
        return getLocalName;
    }

    public void setGetLocalName(String getLocalName) {
        this.getLocalName = getLocalName;
    }

    public String getGetLogoUrl() {
        return getLogoUrl;
    }

    public void setGetLogoUrl(String getLogoUrl) {
        this.getLogoUrl = getLogoUrl;
    }

    public String getGetPhoneNumber() {
        return getPhoneNumber;
    }

    public void setGetPhoneNumber(String getPhoneNumber) {
        this.getPhoneNumber = getPhoneNumber;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "{\"getAddress\":\"" + getAddress + "\"," +
                "\"getEnglishName\":\"" + getEnglishName + "\"," +
                "\"getLocalName\":\"" + getLocalName + "\"," +
                "\"getLogoUrl\":\"" + getLogoUrl + "\"," +
                "\"getPhoneNumber\":\"" + getPhoneNumber + "\"," +
                "\"supplierId\":\"" + supplierId + "\"}";
    }
}

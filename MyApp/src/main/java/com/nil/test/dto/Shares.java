package com.nil.test.dto;

/**
 * Created by liorr on 6/6/18.
 */
public class Shares {
    private String name;
    private Long shareTime;
    private Location shareLocation;
    private String targetApplication;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getShareLocation() {
        return shareLocation;
    }

    public void setShareLocation(Location shareLocation) {
        this.shareLocation = shareLocation;
    }

    public Long getShareTime() {
        return shareTime;
    }

    public void setShareTime(Long shareTime) {
        this.shareTime = shareTime;
    }

    public String getTargetApplication() {
        return targetApplication;
    }

    public void setTargetApplication(String targetApplication) {
        this.targetApplication = targetApplication;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\":\"" + name + "\"," +
                "\"shareLocation\":" + shareLocation + "," +
                "\"shareTime\":" + shareTime.longValue() + "," +
                "\"targetApplication\":\"" + targetApplication + "\"" +
                "}";
    }
}

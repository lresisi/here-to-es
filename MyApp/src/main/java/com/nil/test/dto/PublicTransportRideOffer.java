
package com.nil.test.dto;

import java.util.List;

public class PublicTransportRideOffer {

    private Long estimatedDurationMs;
    private List<Leg> legs = null;
    private Route route;
    private String type;
    private Integer transfers;

    public Long getEstimatedDurationMs() {
        return estimatedDurationMs;
    }

    public void setEstimatedDurationMs(Long estimatedDurationMs) {
        this.estimatedDurationMs = estimatedDurationMs;
    }

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTransfers() {
        return transfers;
    }

    public void setTransfers(Integer transfers) {
        this.transfers = transfers;
    }

    @Override
    public String toString() {
        String a = "{\"estimatedDurationMs\":" + estimatedDurationMs + "," +
                "\"legs\":[";

        for (Leg leg : legs) {
            a += leg.toString();
            a += ", ";
        }

        a = a.substring(0, a.length()-2); // remove comma
        a += "]," +
                "\"route\":{\"destination\":{\"lat\":" + route.getDestination().getLocation().getLat() + "," +
                "\"lon\":" + route.getDestination().getLocation().getLng() + "}," +
                "\"pickup\":{\"lat\":" + route.getPickup().getLocation().getLat() + "," +
                "\"lon\":" + route.getPickup().getLocation().getLng() + "}}," +
                "\"type\":\" " + type + "\"," +
                "\"transfers\":" + transfers + "}";

        return a;
    }
}

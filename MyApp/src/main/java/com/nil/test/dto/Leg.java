
package com.nil.test.dto;

public class Leg {

    private Destination destination;
    private Long estimatedDurationMs;
    private Long length;
    private Origin origin;
    private String transportMode;
    private String lineName;
    private String operatorName;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Long getEstimatedDurationMs() {
        return estimatedDurationMs;
    }

    public void setEstimatedDurationMs(Long estimatedDurationMs) {
        this.estimatedDurationMs = estimatedDurationMs;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "{" +
                "      \"destination\": {" +
                "        \"description\": \"" + destination.getDescription() + "\"," +
                "        \"location\": " + destination.getLocation().toString() +
                "      }," +
                "      \"estimatedDurationMs\": " + estimatedDurationMs + "," +
                "      \"length\": " + length + "," +
                "      \"origin\": {" +
                "        \"description\": \"" + origin.getDescription() + "\"," +
                "        \"location\": " + origin.getLocation().toString() +
                "      }," +
                "      \"transportMode\": \"" + transportMode + "\"" +
                "    }";
    }
}

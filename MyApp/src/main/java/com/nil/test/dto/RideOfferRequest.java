
package com.nil.test.dto;

public class RideOfferRequest {

    private Constraints constraints;
    private String passengerNote;
    private Long prebookPickupTime;
    private RideWaypoints rideWaypoints;

    public Constraints getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }

    public String getPassengerNote() {
        return passengerNote;
    }

    public void setPassengerNote(String passengerNote) {
        this.passengerNote = passengerNote;
    }

    public Long getPrebookPickupTime() {
        return prebookPickupTime;
    }

    public void setPrebookPickupTime(Long prebookPickupTime) {
        this.prebookPickupTime = prebookPickupTime;
    }

    public RideWaypoints getRideWaypoints() {
        return rideWaypoints;
    }

    public void setRideWaypoints(RideWaypoints rideWaypoints) {
        this.rideWaypoints = rideWaypoints;
    }

    @Override
    public String toString() {
        return "{\"constraints\":{\"passengerCount\":" + constraints.getPassengerCount() + "," +
                "\"suitcaseCount\":" + constraints.getSuitcaseCount() + "}," +
                "\"passengerNote\":\"" + passengerNote + "\"," +
                "\"prebookPickupTime\":" + prebookPickupTime + "," +
                "\"rideWaypoints\":{\"destination\":" + rideWaypoints.getDestination().getLocation().toString() + "," +
                "\"pickup\":" + rideWaypoints.getPickup().getLocation().toString() + "}}";
    }
}

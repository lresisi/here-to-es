
package com.nil.test.dto;

public class TaxiRideOffer {

    private String cancellationPolicy;
    private Long estimatedPickupTime;
    private EstimatedPrice estimatedPrice;
    private String offerId;
    private Route route;
    private Supplier supplier;
    private String type;
    private Long expirationTime;

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public Long getEstimatedPickupTime() {
        return estimatedPickupTime;
    }

    public void setEstimatedPickupTime(Long estimatedPickupTime) {
        this.estimatedPickupTime = estimatedPickupTime;
    }

    public EstimatedPrice getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(EstimatedPrice estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    @Override
    public String toString() {
        return "{\"cancellationPolicy\":\"" + cancellationPolicy + "\"," +
                "\"estimatedPickupTime\":" + estimatedPickupTime + "," +
                "\"estimatedPrice\":{\"priceRange\":{\"currencyCode\":\"" + estimatedPrice.getPriceRange().getCurrencyCode() + "\"," +
                "\"lowerBound\":" + estimatedPrice.getPriceRange().getLowerBound() + "," +
                "\"upperBound\":" + estimatedPrice.getPriceRange().getUpperBound() + "}}," +
                "\"offerId\":\"" + offerId + "\"," +
                "\"route\":{\"destination\":" + route.getDestination().getLocation().toString() + "," +
                "\"pickup\":" + route.getPickup().getLocation().toString() + "}," +
                "\"supplier\":" + supplier.toString() + "," +
                "\"type\":\"" + type + "\"," +
                "\"expirationTime\":" + expirationTime + "}";
    }
}

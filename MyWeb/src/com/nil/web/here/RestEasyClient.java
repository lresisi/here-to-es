package com.nil.web.here;

import com.nil.test.Setup;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class RestEasyClient {

    public static void main(String[] args) throws IOException, URISyntaxException {

        ResteasyClient client = new ResteasyClientBuilder().build();
        HashMap<String, String> dtoToIndexMap = getDtoToIndexMap();

        for (URI resourceFile : Setup.getResourceFiles("dto")) {
            try {
                String jsonString = new String(Files.readAllBytes(Paths.get(resourceFile)));
                String fileName = new File(resourceFile).getName();
                String indexName = dtoToIndexMap.get(fileName);
                ResteasyWebTarget target = client.target("http://localhost:8081/service/here/" + indexName);
                Response response = target.request().post(
                        Entity.entity(jsonString, "application/json"));

                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed: HTTP error code : "
                            + response.getStatus());
                }

//                System.out.println("Server response : \n");
                System.out.println(response.readEntity(String.class));
                System.out.println("-----------------------");

                response.close();

            } catch (Exception e) {
                String fileName = new File(resourceFile).getName().replace(".json", "");
                System.out.println("Error with " + fileName + ", exception: " + e.getMessage());
                System.out.println("-----------------------");
            }
        }

        client.close();
    }

    private static HashMap<String, String> getDtoToIndexMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Driver.json", "driver");
        map.put("PassengerDetails.json", "passenger_details");
        map.put("PublicTransportRideOffer.json", "public_transport_ride_offer");
        map.put("RideOfferRequest.json", "ride_offer_request");
        map.put("Supplier.json", "supplier");
        map.put("TaxiRideOffer.json", "taxi_ride_offer");
        map.put("Shares.json", "user_shares");
        return map;
    }
}
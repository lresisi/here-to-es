package com.nil.test;

import com.nil.test.dto.*;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.elasticsearch.action.index.IndexResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by liorr on 6/1/18.
 */
public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        HashMap<String, Class> dtoToClassMap = getDtoToClassMap();

        HashMap<String, URI> dto = getResourceFiles("dto");
//        ArrayList<Object> arr = new ArrayList<>();
//        for (String jsonFilename : dtoToClassMap.keySet()) {
//            String json = new String(Files.readAllBytes(Paths.get(dto.get(jsonFilename))));
//            Object asClass = getAsClass(json, dtoToClassMap.get(jsonFilename));
//            arr.add(asClass);
//        }

        ElasticsearchClient client = new ElasticsearchClient("localhost", 9200);

        HashMap<String, String> hereJsonToDtoMap = getHereJsonToDtoMap();

        for (String mapping : hereJsonToDtoMap.keySet()) {
            if (!mapping.equals("user_shares.json")) continue;
            try {
                String dtoName = hereJsonToDtoMap.get(mapping);
                String json = new String(Files.readAllBytes(Paths.get(dto.get(dtoName))));
                Object asClass = getAsClass(json, dtoToClassMap.get(dtoName));
//                IndexResponse response = client.index(mapping.replace(".json", ""), new Gson().toJson(asClass));
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readValue(asClass.toString(), JsonNode.class);

                IndexResponse response = client.index(mapping.replace(".json", ""), rootNode.toString());
                System.out.println("Response for " + mapping + ": " + response.toString());
                System.out.println("--------------------------------");
            } catch (Exception e) {
                System.out.println("Error for " + mapping);
                System.out.println("--------------------------------");
            }
        }

        client.close();
    }

    private static HashMap<String, Class> getDtoToClassMap() {
        HashMap<String, Class> map = new HashMap<>();
        map.put("Driver.json", Driver.class);
        map.put("PassengerDetails.json", PassengerDetails.class);
        map.put("PublicTransportRideOffer.json", PublicTransportRideOffer.class);
        map.put("RideOfferRequest.json", RideOfferRequest.class);
        map.put("Supplier.json", Supplier.class);
        map.put("TaxiRideOffer.json", TaxiRideOffer.class);
        map.put("Shares.json", Shares.class);
        return map;
    }

    private static HashMap<String, String> getHereJsonToDtoMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("driver.json", "Driver.json");
        map.put("passenger_details.json", "PassengerDetails.json");
        map.put("public_transport_ride_offer.json", "PublicTransportRideOffer.json");
        map.put("ride_offer_request.json", "RideOfferRequest.json");
        map.put("supplier.json", "Supplier.json");
        map.put("taxi_ride_offer.json", "TaxiRideOffer.json");
        map.put("user_shares.json", "Shares.json");
        return map;
    }

    public static HashMap<String, URI> getResourceFiles(String path) throws IOException, URISyntaxException {
        HashMap<String, URI> filenames = new HashMap<>();

        try (InputStream in = Setup.class.getClassLoader().getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.put(resource, Setup.class.getClassLoader().getResource(path + "/" + resource).toURI());
            }
        }

        return filenames;
    }

    private static <T> T getAsClass(String json, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        T rootNode = mapper.readValue(json, clazz);
        return rootNode;
    }
}

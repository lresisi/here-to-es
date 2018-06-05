package com.nil.web.here;

/**
 * Created by liorr on 5/28/18.
 */

import com.nil.test.ElasticsearchClient;
import com.nil.test.dto.*;
import org.elasticsearch.action.index.IndexResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;

@Path("/")
public class HereService {
    public static final String APPLICATION_JSON = "application/json";
    public static final String BASE_PATH = "/here/";

    @Context
    private HttpServletRequest httpRequest;

    @Context
    private HttpServletResponse httpResponse;

    private ElasticsearchClient client;

    /**
     * @param env provides access to ServletConfig and ServletContext
     */
    public HereService(final Map<Class, Object> env) {
        this.client = new ElasticsearchClient("localhost", 9200);
    }

    @POST
    @Path(BASE_PATH + "driver")
    @Consumes(APPLICATION_JSON)
    public Response add(Driver driver) {
        try {
            if (driver.getDrivingLicenseId() != null) {
                return handleInsert("driver", driver.toString(), driver.getDrivingLicenseId());
            } else {
                return handleInsert("driver", driver.toString());
            }

        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    @POST
    @Path(BASE_PATH + "passenger_details")
    @Consumes(APPLICATION_JSON)
    public Response add(PassengerDetails passengerDetails) {
        try {
            if (passengerDetails.getEmail() != null) {
                return handleInsert("passenger_details", passengerDetails.toString(), passengerDetails.getEmail().replace("@", "_").replace(".", "_"));
            } else {
                return handleInsert("passenger_details", passengerDetails.toString());
            }
        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    @POST
    @Path(BASE_PATH + "public_transport_ride_offer")
    @Consumes(APPLICATION_JSON)
    public Response add(PublicTransportRideOffer publicTransportRideOffer) {
        try {
            return handleInsert("public_transport_ride_offer", publicTransportRideOffer.toString());
        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    @POST
    @Path(BASE_PATH + "ride_offer_request")
    @Consumes(APPLICATION_JSON)
    public Response add(RideOfferRequest rideOfferRequest) {
        try {
            return handleInsert("ride_offer_request", rideOfferRequest.toString());
        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    @POST
    @Path(BASE_PATH + "supplier")
    @Consumes(APPLICATION_JSON)
    public Response add(Supplier supplier) {
        try {
            if (supplier.getSupplierId() != null) {
                return handleInsert("supplier", supplier.toString(), supplier.getSupplierId());
            } else {
                return handleInsert("supplier", supplier.toString());
            }
        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    @POST
    @Path(BASE_PATH + "shares")
    @Consumes(APPLICATION_JSON)
    public Response add(Shares shares) {
        try {
            return handleInsert("user_shares", shares.toString());
        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    @POST
    @Path(BASE_PATH + "taxi_ride_offer")
    @Consumes(APPLICATION_JSON)
    public Response add(TaxiRideOffer taxiRideOffer) {
        try {
            if (taxiRideOffer.getOfferId() != null) {
                return handleInsert("taxi_ride_offer", taxiRideOffer.toString(), taxiRideOffer.getOfferId());
            } else {
                return handleInsert("taxi_ride_offer", taxiRideOffer.toString());
            }

        } catch (IOException e) {
            return exceptionResolver(e);
        }
    }

    // TODO remove
    @GET
    @Path(BASE_PATH + "lior")
//    @Consumes(APPLICATION_JSON)
    public Response add() {
        return Response.ok("Success with lior").build();
    }

    private Response handleInsert(String index, String json) throws IOException {
        IndexResponse response = client.index(index, json);
        return Response.ok("Success for index " + index + ", " + response.toString()).build();
    }

    private Response handleInsert(String index, String json, String key) throws IOException {
        IndexResponse response = client.index(index, json, key);
        return Response.ok("Success for index " + index + ", " + response.toString()).build();
    }

    private Response exceptionResolver(Exception e) {
        return Response.status(567, "There has been an error: " + e.getMessage()).build();
    }
}

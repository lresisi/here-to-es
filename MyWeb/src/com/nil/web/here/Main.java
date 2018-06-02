package com.nil.web.here;


import com.nil.test.ElasticsearchClient;

/**
 * Created by liorr on 5/26/18.
 */
public class Main {
    public static void main(String[] args) {
        try {
            ElasticsearchClient client = new ElasticsearchClient("localhost", 9200);
            client.index("a", getExampleJson());
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String getExampleJson() {
        String json;
        json = "{\"constraints\": {\n" +
                "   \"passengers_no\": 1,\n" +
                "   \"suitcases_no\": 1\n" +
                " },\n" +
                " \"passenger_note\": \"note\",\n" +
                " \"prebook_pickup_time_ms\": 1527441268538,\n" +
                " \"route\": {\n" +
                "   \"destination\": {\n" +
                "     \"point\": {\n" +
                "       \"lat\": 51.55776,\n" +
                "       \"lng\": -0.14117\n" +
                "     }\n" +
                "   },\n" +
                "   \"pickup\": {\n" +
                "     \"point\": {\n" +
                "       \"lat\": 51.50177,\n" +
                "       \"lng\": -0.10941\n" +
                "     }\n" +
                "   }\n" +
                " }\n" +
                "}";
        return json;
    }

}

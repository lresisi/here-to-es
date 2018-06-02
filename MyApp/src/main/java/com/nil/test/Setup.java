package com.nil.test;

import org.elasticsearch.client.Response;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liorr on 6/1/18.
 */
public class Setup {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ElasticsearchClient client = new ElasticsearchClient("localhost", 9200);

        deleteAllIndices(client);
        System.out.println("---------------------");
        createAllIndices(client);

        client.close();
    }

    private static void createAllIndices(ElasticsearchClient client) throws IOException, URISyntaxException {
        for (URI resourceFile : getResourceFiles("mappings")) {
            String fileName = new File(resourceFile).getName().replace(".json", "");
            try {
                Response response = client.createIndex(fileName, resourceFile);
                System.out.println("Creating index for " + fileName + " returned " + response.getStatusLine().getStatusCode());
            } catch (Exception e) {
                System.out.println("Creating index for " + fileName + " failed: " + e.getMessage());
            }
        }
    }

    private static void deleteAllIndices(ElasticsearchClient client) throws IOException, URISyntaxException {
        for (URI resourceFile : getResourceFiles("mappings")) {
            String fileName = new File(resourceFile).getName().replace(".json", "");
            try {
                Response response = client.deleteIndex(fileName);
                System.out.println("Deleting index for " + fileName + " returned " + response.getStatusLine().getStatusCode());
            } catch (Exception e) {
                System.out.println("Deleting index for " + fileName + " failed: " + e.getMessage());
            }
        }
    }

    public static List<URI> getResourceFiles(String path) throws IOException, URISyntaxException {
        List<URI> filenames = new ArrayList<>();

        try (InputStream in = Setup.class.getClassLoader().getResourceAsStream(path);
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;
            while ((resource = br.readLine()) != null) {
                filenames.add(Setup.class.getClassLoader().getResource(path + "/" + resource).toURI());
            }
        }

        return filenames;
    }
}

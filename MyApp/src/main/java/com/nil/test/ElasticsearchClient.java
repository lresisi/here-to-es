package com.nil.test;

import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.elasticsearch.action.bulk.BackoffPolicy;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Created by liorr on 5/29/18.
 */
public class ElasticsearchClient {

    private String host;
    private int port;
    private RestHighLevelClient client;
    private BulkProcessor bulkProcessor;

    /**
     * Define the behavior immediately before sending the bulk to ES and after receiving its response,
     * either upon success or failure
     */
    private BulkProcessor.Listener listener = new BulkProcessor.Listener() {
        /**
         * Callback before the bulk is executed.
         */
        public void beforeBulk(long executionId, BulkRequest request) {
        }

        /**
         * Callback after a successful execution of bulk request.
         */
        public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
            if (response.hasFailures()) {
            }
        }

        /**
         * Callback after a failed execution of bulk request.
         */
        public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
        }
    };

    public ElasticsearchClient(String host, int port) {
        this.host = host;
        this.port = port;
        initClient();
        initBulkProcessorBuilder();
    }

    private void initClient() {
        this.client = new RestHighLevelClient(RestClient.builder(
                new HttpHost(host, port)));
    }

    private void initBulkProcessorBuilder() {
        bulkProcessor = BulkProcessor.builder(client::bulkAsync, listener)
                .setBulkSize(new ByteSizeValue(1, ByteSizeUnit.MB))
                .setConcurrentRequests(0)
                .setBackoffPolicy(BackoffPolicy.constantBackoff(TimeValue.timeValueMillis(1000L), 3))
                .setFlushInterval(TimeValue.timeValueSeconds(1))
                .build();
    }

    public void close() throws IOException {
        bulkProcessor.close();
        client.close();
    }

    public GetResponse get(String index, String id) throws IOException {
        GetRequest getRequest = new GetRequest(index).type("_doc").id(id);
        GetResponse getResponse = client.get(getRequest);
        return getResponse;
    }

    public void bulkIndex(String index, String json) {
        IndexRequest indexRequest = new IndexRequest(index, "_doc");
        indexRequest.source(json, XContentType.JSON);
        bulkProcessor.add(indexRequest);
        // return client.index(indexRequest);
    }

    public IndexResponse index(String index, String json) throws IOException {
        IndexRequest indexRequest = new IndexRequest(index, "_doc");
        indexRequest.source(json, XContentType.JSON);
        return client.index(indexRequest);
    }

    /**
     * Create an index
     */
    public Response createIndex(String index, URI mappingResource) throws IOException, URISyntaxException {
        String mapping = new String(Files.readAllBytes(Paths.get(mappingResource)));
        StringEntity entity = new StringEntity(mapping, ContentType.APPLICATION_JSON);
        Response response = client.getLowLevelClient().performRequest("PUT", "/" + index, Collections.emptyMap(), entity);
        return response;
    }

    /**
     * Create an index
     */
    public Response deleteIndex(String index) throws IOException, URISyntaxException {
        Response response = client.getLowLevelClient().performRequest("DELETE", "/" + index);
        return response;
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        ElasticsearchClient a = new ElasticsearchClient("localhost", 9200);
        a.createIndex("temp_index", ElasticsearchClient.class.getClassLoader().getResource("mapping.json").toURI());
    }


}

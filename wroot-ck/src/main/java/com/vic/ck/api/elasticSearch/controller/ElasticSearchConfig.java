package com.vic.ck.api.elasticSearch.controller;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;

public class ElasticSearchConfig {
    public TransportClient createClient() throws IOException {
        Settings settings = Settings.builder().put("cluster.name", "elasticSearch").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        return client;
    }
}

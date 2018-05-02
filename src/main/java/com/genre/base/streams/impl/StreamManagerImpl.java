package com.genre.base.streams.impl;

import com.genre.base.scraper.CraigslistScrape;
import com.genre.base.streams.StreamManager;
import com.genre.base.streams.processor.MyProcessor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class StreamManagerImpl implements StreamManager {

    @Autowired
    CraigslistScrape craigslistScrape;



    public void startStream() {



        Properties config = initConfig();

        Topology builder = new Topology();
        builder.addSource("Source-Name", "Topic-Name");
        builder.addProcessor("MyProcessor", () -> new MyProcessor(craigslistScrape));

        builder.addStateStore(getStateStore(), "MyProcessor");

        KafkaStreams streams = new KafkaStreams(builder, config);

        streams.start();

    }

    public Properties initConfig() {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "My-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "My-application");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.STATE_DIR_CONFIG, "My-application");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return config;
    }


    StoreBuilder<KeyValueStore<String, String>> getStateStore(){
        StoreBuilder<KeyValueStore<String, String>> stateStore = Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore("persistant-store"),
                Serdes.String(),
                Serdes.String()
        );
        return stateStore;

    }

}

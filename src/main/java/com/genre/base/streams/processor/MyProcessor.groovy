package com.genre.base.streams.processor

import com.genre.base.scraper.CraigslistScrape
import org.apache.kafka.streams.processor.Processor
import org.apache.kafka.streams.processor.ProcessorContext
import org.apache.kafka.streams.state.KeyValueStore

import javax.xml.crypto.dsig.keyinfo.KeyValue

class MyProcessor implements Processor<String, String> {

    // inject stateful dep
    CraigslistScrape craigslistScrape


    // processor context
    ProcessorContext processorContext

    KeyValueStore<String, String> persistantStore


    // inject stateful dep
    public MyProcessor(CraigslistScrape craigslistScrape){
        this.craigslistScrape = craigslistScrape
    }


    @Override
    void init(ProcessorContext processorContext) {
        this.processorContext = processorContext
        this.persistantStore = (KeyValueStore<String, String>) this.processorContext.getStateStore("persistant-store")
    }

    @Override
    void process(String s, String s2) {

    }

    @Override
    void punctuate(long l) {

    }

    @Override
    void close() {

    }
}

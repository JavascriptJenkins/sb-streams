package com.genre.base.streams.controller

import com.genre.base.streams.StreamManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/KafkaStreamController/")
@RestController
class KafkaStreamController {

    @Autowired
    StreamManager streamManager

    @RequestMapping("startStream")
    @ResponseStatus(value = HttpStatus.OK)
    void StartStream(){
        streamManager.startStream()
    }



}

package com.fred.schoolmanagement.controller;


import com.fred.schoolmanagement.dto.StreamDTO;
import com.fred.schoolmanagement.dto.StreamSaveDTO;
import com.fred.schoolmanagement.dto.StreamUpdateDTO;
import com.fred.schoolmanagement.service.interfaces.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/streams")
public class StreamController {
    private StreamService streamService;

    @Autowired
    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping(path = "/add-stream")
    public ResponseEntity<String> addStream(@RequestBody StreamSaveDTO streamSaveDTO){
        String response = streamService.addStream(streamSaveDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/get-all-streams")
    public ResponseEntity<List<StreamDTO>> getAllStreams(){
        List<StreamDTO> allStreams = streamService.getAllStreams();
        return ResponseEntity.ok(allStreams);
    }

    @PutMapping(path = "/update-streams/{id}")
    public ResponseEntity<String> updateStream(@PathVariable("id")long id, StreamUpdateDTO streamUpdateDTO){
        String response = streamService.updateStream(id, streamUpdateDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/delete-stream/{id}")
    public String deleteStream(@PathVariable("id") long id){
        boolean deleteStream = streamService.deleteStream(id);

        if (deleteStream){
            return "Stream Deleted Successfully";
        } else {
            return "Stream ID not Found";
        }
    }
}

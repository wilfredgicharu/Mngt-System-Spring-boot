package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.StreamDTO;
import com.fred.schoolmanagement.dto.StreamSaveDTO;
import com.fred.schoolmanagement.dto.StreamUpdateDTO;
import com.fred.schoolmanagement.entity.Stream;
import com.fred.schoolmanagement.entity.Student;
import com.fred.schoolmanagement.repository.StreamRepository;
import com.fred.schoolmanagement.repository.StudentRepository;
import com.fred.schoolmanagement.service.interfaces.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StreamServiceImplementation implements StreamService {

    private StreamRepository streamRepository;
    private StudentRepository studentRepository;

    @Autowired
    public StreamServiceImplementation(StreamRepository streamRepository, StudentRepository studentRepository) {
        this.streamRepository = streamRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public String addStream(StreamSaveDTO streamSaveDTO) {
        Optional<Stream> streamCodeExists = streamRepository.findByStreamCode(streamSaveDTO.getStreamCode());
        if (streamCodeExists.isPresent()){
            return "Stream with that code already exists";
        }

        Optional<Stream> streamNameExists = streamRepository.findByStreamName(streamSaveDTO.getStreamName());
        if (streamNameExists.isPresent()){
            return "Stream with that Name already exists";
        }

        Stream stream = new Stream(
                streamSaveDTO.getStreamCode(),
                streamSaveDTO.getStreamName()
        );

        streamRepository.save(stream);

        return "Stream Saved successfully";

    }

    @Override
    public List<StreamDTO> getAllStreams() {
        List<Stream> getStreams = streamRepository.findAll();
        List<StreamDTO> streamDTOList = new ArrayList<>();

        for (Stream stream: getStreams){
            StreamDTO streamDTO = new StreamDTO(
                    stream.getStreamId(),
                    stream.getStreamCode(),
                    stream.getStreamName()
            );

            streamDTOList.add(streamDTO);
        }
        return streamDTOList;
    }

    @Override
    public String updateStream(long id, StreamUpdateDTO streamUpdateDTO) {
        if (streamRepository.existsById(id)){
            Stream stream = streamRepository.getById(id);
            if (streamUpdateDTO.getStreamCode() != 0){
                stream.setStreamCode(streamUpdateDTO.getStreamCode());
            }

            if (streamUpdateDTO.getStreamName() != null){
                stream.setStreamName(streamUpdateDTO.getStreamName());
            }

            streamRepository.save(stream);
            return "Stream Details submitted successfully";

        } else {
            return "Stream with that ID does not exist";
        }
    }

    @Override
    public boolean deleteStream(long id) {
        if (streamRepository.existsById(id)){
            streamRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

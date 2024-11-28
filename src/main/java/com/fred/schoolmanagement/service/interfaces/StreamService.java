package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.StreamDTO;
import com.fred.schoolmanagement.dto.StreamSaveDTO;
import com.fred.schoolmanagement.dto.StreamUpdateDTO;

import java.util.List;

public interface StreamService {

    String addStream(StreamSaveDTO streamSaveDTO);

    List<StreamDTO> getAllStreams();

    String updateStream(long id, StreamUpdateDTO streamUpdateDTO);

    boolean deleteStream(long id);

}

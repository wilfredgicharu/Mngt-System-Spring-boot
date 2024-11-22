package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.BatchDTO;
import com.fred.schoolmanagement.dto.BatchSaveDTO;
import com.fred.schoolmanagement.dto.BatchUpdateDTO;

import java.util.List;

public interface BatchService {

    String addBatch(BatchSaveDTO batchSaveDTO);

    List<BatchDTO> getAllBatches();

    String updateBatch(long id, BatchUpdateDTO batchUpdateDTO);

}

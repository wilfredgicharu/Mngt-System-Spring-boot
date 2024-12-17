package com.fred.schoolmanagement.service.implimentation;


import com.fred.schoolmanagement.dto.BatchDTO;
import com.fred.schoolmanagement.dto.BatchSaveDTO;
import com.fred.schoolmanagement.dto.BatchUpdateDTO;
import com.fred.schoolmanagement.entity.Batch;
import com.fred.schoolmanagement.repository.BatchRepository;
import com.fred.schoolmanagement.repository.CourseRepository;
import com.fred.schoolmanagement.service.interfaces.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchServiceImplementation implements BatchService {

    private BatchRepository batchRepository;
    private CourseRepository courseRepository;


    @Autowired
    public BatchServiceImplementation(BatchRepository batchRepository, CourseRepository courseRepository) {
        this.batchRepository = batchRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public String addBatch(BatchSaveDTO batchSaveDTO) {
        try {
            Batch batch = new Batch(
                    batchSaveDTO.getBatchName(),
                    batchSaveDTO.getStartDate(),
                    courseRepository.getById(batchSaveDTO.getCourseId())
            );
            batchRepository.save(batch);
            System.out.println("\n Message: Batch saved successfully");

            return batch.getBatchName();

        } catch (Exception ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<BatchDTO> getAllBatches() {
        List<Batch> getBatch = batchRepository.findAll();
        List<BatchDTO> batchDTOList = new ArrayList<>();

        for (Batch batch : getBatch){
            BatchDTO batchDTO = new BatchDTO(
                    batch.getBatchId(),
                    batch.getBatchName(),
                    batch.getCourse(),
                    batch.getStartDate()
            );
            batchDTOList.add(batchDTO);
        }
        return batchDTOList;
    }

    @Override
    public String updateBatch(long id, BatchUpdateDTO batchUpdateDTO) {
        if (batchRepository.existsById(id)){
            Batch batch = batchRepository.getById(id);
            batch.setBatchName(batchUpdateDTO.getBatchName());
            batch.setCourse(courseRepository.getById(batchUpdateDTO.getCourseId()));
            batch.setStartDate(batchUpdateDTO.getStartDate());

            batchRepository.save(batch);
            System.out.println("\n batch updated successfully");
            return "batch details updated successfully";
        } else {
            System.out.println("\nBatch ID not Found");
            return "Batch ID not Found";
        }

    }

    @Override
    public boolean deleteBatch(long id){
        if (batchRepository.existsById(id)){
            batchRepository.deleteById(id);
            System.out.println("\nBatch deleted successfully");
            return true;
        } else {
            System.out.println("\nBatch not found");
            return false;
        }
    }

}

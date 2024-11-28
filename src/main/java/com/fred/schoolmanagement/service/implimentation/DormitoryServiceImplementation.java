package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.DormitoryDTO;
import com.fred.schoolmanagement.dto.DormitorySaveDTO;
import com.fred.schoolmanagement.dto.DormitoryUpdateDTO;
import com.fred.schoolmanagement.entity.Dormitory;
import com.fred.schoolmanagement.repository.DormitoryRepository;
import com.fred.schoolmanagement.service.interfaces.DormitoryService;
import org.hibernate.event.spi.PreInsertEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DormitoryServiceImplementation implements DormitoryService {

    private DormitoryRepository dormitoryRepository;

    @Autowired
    public DormitoryServiceImplementation(DormitoryRepository dormitoryRepository) {
        this.dormitoryRepository = dormitoryRepository;
    }

    @Override
    public String addDormitory(DormitorySaveDTO dormitorySaveDTO) {
        Optional<Dormitory> dormCodeExists = dormitoryRepository.findByDormCode(dormitorySaveDTO.getDormCode());
        Optional<Dormitory> dormNameExists = dormitoryRepository.findByDormName(dormitorySaveDTO.getDormName());

        if (dormCodeExists.isPresent()){
            return "Dorm with that code Already exists";
        }
        if (dormNameExists.isPresent()){
            return "Dorm with that code Already exists";
        }
        Dormitory dormitory = new Dormitory
                (
                        dormitorySaveDTO.getDormCode(),
                        dormitorySaveDTO.getDormName()
                );
        dormitoryRepository.save(dormitory);
        return "Dorm created successfully";
    }

    @Override
    public List<DormitoryDTO> getAllDormitories() {
        List<Dormitory> getDormitories = dormitoryRepository.findAll();
        List<DormitoryDTO> dormitoryDTOList = new ArrayList<>();

        for (Dormitory dormitory: getDormitories){
            DormitoryDTO dormitoryDTO = new DormitoryDTO(
                    dormitory.getDormitoryId(),
                    dormitory.getDormCode(),
                    dormitory.getDormName()
            );
            dormitoryDTOList.add(dormitoryDTO);
        }
        return dormitoryDTOList;
    }

    @Override
    public String updateDormitory(long id, DormitoryUpdateDTO dormitoryUpdateDTO) {
        if (dormitoryRepository.existsById(id)){
            Dormitory dormitory = dormitoryRepository.getById(id);
            if (dormitoryUpdateDTO.getDormCode() != 0){
                dormitory.setDormCode(dormitoryUpdateDTO.getDormCode());
            }
            if (dormitoryUpdateDTO.getDormName() != null){
                dormitory.setDormName(dormitoryUpdateDTO.getDormName());
            }

            dormitoryRepository.save(dormitory);
            return "dormitory updated successfully";
        }
        else {
            return "Dormitory with that ID doesn't exist!!";
        }
    }

    @Override
    public boolean deleteDormitory(long id) {
        if (dormitoryRepository.existsById(id)){
            dormitoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.DormitoryDTO;
import com.fred.schoolmanagement.dto.DormitorySaveDTO;
import com.fred.schoolmanagement.dto.DormitoryUpdateDTO;

import java.util.List;

public interface DormitoryService {
    String addDormitory(DormitorySaveDTO dormitorySaveDTO);

    List<DormitoryDTO> getAllDormitories();

    String updateDormitory(long id, DormitoryUpdateDTO dormitoryUpdateDTO);

    boolean deleteDormitory(long id);
}

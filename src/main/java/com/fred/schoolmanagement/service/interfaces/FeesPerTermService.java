package com.fred.schoolmanagement.service.interfaces;

import com.fred.schoolmanagement.dto.FeesPerTermDTO;
import com.fred.schoolmanagement.dto.FeesPerTermSaveDTO;
import com.fred.schoolmanagement.dto.FeesPerTermUpdateDTO;

import java.util.List;

public interface FeesPerTermService {

    String addFeesPerTerm(FeesPerTermSaveDTO feesPerTermSaveDTO);

    List<FeesPerTermDTO> getAllFeesPerTerm();

    String updateFeesPerTerm(long id, FeesPerTermUpdateDTO feesPerTermUpdateDTO);

    boolean deleteFeesPerTerm(long id);

}

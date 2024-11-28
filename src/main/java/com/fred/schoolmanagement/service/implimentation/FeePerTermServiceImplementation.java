package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.FeesPerTermDTO;
import com.fred.schoolmanagement.dto.FeesPerTermSaveDTO;
import com.fred.schoolmanagement.dto.FeesPerTermUpdateDTO;
import com.fred.schoolmanagement.repository.FeesPerTermRepository;
import com.fred.schoolmanagement.service.interfaces.FeesPerTermService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeePerTermServiceImplementation implements FeesPerTermService {

    FeesPerTermRepository feesPerTermRepository;

    public FeePerTermServiceImplementation(FeesPerTermRepository feesPerTermRepository) {
        this.feesPerTermRepository = feesPerTermRepository;
    }

    @Override
    public String addFeesPerTerm(FeesPerTermSaveDTO feesPerTermSaveDTO) {
        return null;
    }

    @Override
    public List<FeesPerTermDTO> getAllFeesPerTerm() {
        return null;
    }

    @Override
    public String updateFeesPerTerm(long id, FeesPerTermUpdateDTO feesPerTermUpdateDTO) {
        return null;
    }

    @Override
    public boolean deleteFeesPerTerm(long id) {
        return false;
    }
}

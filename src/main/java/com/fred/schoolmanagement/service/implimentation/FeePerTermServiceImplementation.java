package com.fred.schoolmanagement.service.implimentation;

import com.fred.schoolmanagement.dto.FeesPerTermDTO;
import com.fred.schoolmanagement.dto.FeesPerTermSaveDTO;
import com.fred.schoolmanagement.dto.FeesPerTermUpdateDTO;
import com.fred.schoolmanagement.entity.FeesPerTerm;
import com.fred.schoolmanagement.repository.FeesPerTermRepository;
import com.fred.schoolmanagement.service.interfaces.FeesPerTermService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeePerTermServiceImplementation implements FeesPerTermService {

    FeesPerTermRepository feesPerTermRepository;

    public FeePerTermServiceImplementation(FeesPerTermRepository feesPerTermRepository) {
        this.feesPerTermRepository = feesPerTermRepository;
    }

    @Override
    public String addFeesPerTerm(FeesPerTermSaveDTO feesPerTermSaveDTO) {

            FeesPerTerm feesPerTerm = new FeesPerTerm(
                    feesPerTermSaveDTO.getTerm(),
                    feesPerTermSaveDTO.getFeeCharged(),
                    feesPerTermSaveDTO.getTermEndDate(),
                    feesPerTermSaveDTO.getTermStartDate()
            );

            feesPerTermRepository.save(feesPerTerm);

            return "Fee for term :" + feesPerTermSaveDTO.getTerm() + "added successfully";
    }

    @Override
    public List<FeesPerTermDTO> getAllFeesPerTerm() {
        List<FeesPerTerm> getFees = feesPerTermRepository.findAll();
        List<FeesPerTermDTO> feeDTOList = new ArrayList<>();

        for (FeesPerTerm fees: getFees)
        {
            FeesPerTermDTO feesPerTermDTO = new FeesPerTermDTO(
                    fees.getId(),
                    fees.getTerm(),
                    fees.getTermStartDate(),
                    fees.getTermEndDate(),
                    fees.getFeeCharged()
            );
            feeDTOList.add(feesPerTermDTO);
        }
        return feeDTOList;
    }

    @Override
    public String updateFeesPerTerm(long id, FeesPerTermUpdateDTO feesPerTermUpdateDTO) {
        if (feesPerTermRepository.existsById(id)){
            FeesPerTerm feesPerTerm = feesPerTermRepository.getById(id);

            if (feesPerTermUpdateDTO.getTerm() !=0){
                feesPerTerm.setTerm(feesPerTermUpdateDTO.getTerm());
            }
            if (feesPerTermUpdateDTO.getTermStartDate() != null){
                feesPerTerm.setTermStartDate(feesPerTermUpdateDTO.getTermStartDate());
            }
            if (feesPerTermUpdateDTO.getTermEndDate() != null){
                feesPerTerm.setTermEndDate(feesPerTermUpdateDTO.getTermEndDate());
            }
            if (feesPerTermUpdateDTO.getFeeCharged() !=0){
                feesPerTermUpdateDTO.setFeeCharged(feesPerTermUpdateDTO.getFeeCharged());
            }

            feesPerTermRepository.save(feesPerTerm);

            return "Fee per Term updated successful";
        } else {
            return "fees perTerm Id not Found";
        }
    }

    @Override
    public boolean deleteFeesPerTerm(long id) {
        if (feesPerTermRepository.existsById(id)){
            feesPerTermRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

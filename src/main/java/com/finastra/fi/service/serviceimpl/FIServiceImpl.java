package com.finastra.fi.service.serviceimpl;

import com.finastra.fi.dao.FIDao;
import com.finastra.fi.dao.dto.FIDetailsDTO;
import com.finastra.fi.dao.dto.FIModulesDTO;
import com.finastra.fi.dao.dto.FiModuleMappingDTO;
import com.finastra.fi.response.FIPagingResponse;
import com.finastra.fi.service.FIService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FIServiceImpl implements FIService {
    private static final Logger LOGGER = LogManager.getLogger(FIServiceImpl.class);
    @Autowired
    private FIDao fiDao;

    @Transactional
    @Override
    public FIDetailsDTO getFinanacialInstitutionById(Long id) {
        return fiDao.findById(id);
    }

    @Transactional
    @Override
    public FIDetailsDTO saveFinanacialInstitution(FIDetailsDTO fiDetailsDTO) {

        if (fiDetailsDTO.getModules().size() == 0) {
            LOGGER.error("No Modules selected for FI ");
            throw new IllegalArgumentException("No Modules selected for FI");
        }

        List<FiModuleMappingDTO> moduleMappings = IntStream.range(0, fiDetailsDTO.getModules().size())
                .mapToObj(index -> {
                    String moduleId = fiDetailsDTO.getModules().get(index);

                    System.out.println("moduleId " + moduleId);

                    FiModuleMappingDTO mapping = new FiModuleMappingDTO();
                    mapping.setModuleId(Long.valueOf(moduleId));
                    mapping.setOpId(fiDetailsDTO.getOpId());
                    return mapping;
                }).collect(Collectors.toList());

        fiDetailsDTO.setFiModuleMapping(moduleMappings);

        return fiDao.save(fiDetailsDTO);

    }

    @Transactional
    @Override
    public FIDetailsDTO updateFinanacialInstitution(Long id, FIDetailsDTO fiDetailsDTO) {

        fiDetailsDTO.setId(id);
        List<FiModuleMappingDTO> moduleMappings = IntStream.range(0, fiDetailsDTO.getModules().size())
                .mapToObj(index -> {
                    String moduleId = fiDetailsDTO.getModules().get(index);

                    FiModuleMappingDTO mapping = new FiModuleMappingDTO();
                    mapping.setFiId(id);
                    mapping.setModuleId(Long.valueOf(moduleId));
                    mapping.setOpId(fiDetailsDTO.getOpId());
                    return mapping;
                }).collect(Collectors.toList());

        fiDetailsDTO.setFiModuleMapping(moduleMappings);

        return fiDao.update(id, fiDetailsDTO);
    }

    @Transactional
    @Override
    public String deleteFinanacialInstitution(Long id) {
        return fiDao.deleteById(id);
    }

    @Override
    public FIPagingResponse getAllFinanacialInstitutionsWithPagination(Integer offset, Integer limit) {

        Page<FIDetailsDTO> pageable = fiDao.getAllFIDetailsByPagination(offset, limit);

        List<FIDetailsDTO> resultList = pageable.getContent();

        FIPagingResponse pageResponse = new FIPagingResponse();
        resultList =resultList.stream().skip(1).collect(Collectors.toList());

        pageResponse.setContent(resultList);
        pageResponse.setPageNumber(pageable.getNumber());
        pageResponse.setPageSize(pageable.getSize());
        pageResponse.setTotalElements(pageable.getTotalElements());

        pageResponse.setTotalPages(pageable.getTotalPages());
        pageResponse.setLastPage(pageable.isLast());
        return pageResponse;
    }

    @Override
    public List<FIModulesDTO> getAllModules() {
        return fiDao.getAllModules();
    }

}

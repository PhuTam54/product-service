package com.example.productservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.productservice.dto.DemoGitDTO;
import com.example.productservice.entities.DemoGit;
import com.example.productservice.exception.CategoryNotFoundException;
import com.example.productservice.exception.CustomException;
import com.example.productservice.mapper.DemoGitMapper;
import com.example.productservice.repositories.DemoGitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemoGitServiceImpl implements DemoGitService {

    private final DemoGitRepository demoGitRepository;

    @Override
    public Page<DemoGitDTO> getAllDemoGit(Pageable pageable) {
        Page<DemoGit> categories = demoGitRepository.findByDeletedAtIsNull(pageable);
        return categories.map(DemoGitMapper.INSTANCE::demoGitToDemoGitDTO);
    }

    @Override
    public DemoGitDTO getDemoGitById(Long id) {
        Optional<DemoGit> demogit = demoGitRepository.findById(id);
        if (demogit.isEmpty()) {
            throw new RuntimeException("Can not find demogit with id " + id);
        }
        return DemoGitMapper.INSTANCE.demoGitToDemoGitDTO(demogit.get());
    }

    @Override
    public DemoGitDTO addDemoGit(DemoGitDTO demogitDTO) {

        if (demoGitRepository.existsByDemoGitName(demogitDTO.getDemogitName())) {
            throw new CustomException("DemoGit already exists with name: " + demogitDTO.getDemogitName(),
                    HttpStatus.BAD_REQUEST);
        }

        DemoGit demogit = DemoGitMapper.INSTANCE.demoGitDTOToDemoGit(demogitDTO);
        demoGitRepository.save(demogit);
        return demogitDTO;
    }

    @Override
    public void updateDemoGit(Long id, DemoGitDTO updatedDemoGitDTO) {
        DemoGit demogit = demoGitRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("DemoGit not found with id: " + id));

        if (demogit.getDeletedAt() != null) {
            throw new CustomException("DemoGit is in trash with id: " + id, HttpStatus.BAD_REQUEST);
        }

        if (demoGitRepository.existsByDemoGitName(updatedDemoGitDTO.getDemogitName())) {
            throw new CustomException("DemoGit already exists with name: " + updatedDemoGitDTO.getDemogitName(),
                    HttpStatus.BAD_REQUEST);
        }

        demogit.setDemoGitName(updatedDemoGitDTO.getDemogitName());
        demogit.setDescription(updatedDemoGitDTO.getDescription());

        demoGitRepository.save(demogit);
    }

    @Override
    public void deleteDemoGit(Long id) {
        demoGitRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("DemoGit not found with id: " + id));

        demoGitRepository.deleteById(id);
    }

    @Override
    public List<DemoGitDTO> getDemoGitByName(String name) {
        List<DemoGit> categories = demoGitRepository.findCategoriesByDemoGitName(name);

        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("DemoGit not found with name: " + name);
        }
        List<DemoGitDTO> demogitDTOS = new ArrayList<>();
        for (DemoGit demogit : categories) {
            demogitDTOS.add(DemoGitMapper.INSTANCE.demoGitToDemoGitDTO(demogit));
        }
        return demogitDTOS;
    }

    public void moveToTrash(Long id) {
        DemoGit demogit = demoGitRepository.findById(id).orElse(null);
        if (demogit == null) {
            throw new CategoryNotFoundException("Cannot find this demogit id: " + id);
        }
        LocalDateTime now = LocalDateTime.now();
        demogit.setDeletedAt(now);
        demoGitRepository.save(demogit);
    }

    @Override
    public Page<DemoGitDTO> getInTrash(Pageable pageable) {
        Page<DemoGit> categories = demoGitRepository.findByDeletedAtIsNotNull(pageable);
        return categories.map(DemoGitMapper.INSTANCE::demoGitToDemoGitDTO);
    }

}
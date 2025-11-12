package com.example.productservice.services;

import com.example.productservice.dto.DemoGitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DemoGitService {
    
    Page<DemoGitDTO> getAllDemoGit(Pageable pageable);

    DemoGitDTO getDemoGitById(Long id);

    DemoGitDTO addDemoGit(DemoGitDTO demogitDTO);

    void updateDemoGit(Long id, DemoGitDTO demogitDTO);

    void deleteDemoGit(Long id);

    List<DemoGitDTO> getDemoGitByName(String name);

    void moveToTrash(Long id);

    Page<DemoGitDTO> getInTrash(Pageable pageable);
}

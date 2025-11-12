package com.example.productservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoGitDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demogitId;
    private String demogitName;
    private String description;
}

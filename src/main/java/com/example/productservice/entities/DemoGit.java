package com.example.productservice.entities;

import com.example.productservice.entities.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class DemoGit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demoGitId;

    @Column(nullable = false)
    private String demoGitName;

    private String description;

    @Override
    public String toString() {
        return "Category{" +
                "name='" + demoGitName + '\'' +
                '}';
    }
}

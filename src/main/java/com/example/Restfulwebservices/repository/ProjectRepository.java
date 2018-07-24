package com.example.Restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Restfulwebservices.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> { 

}

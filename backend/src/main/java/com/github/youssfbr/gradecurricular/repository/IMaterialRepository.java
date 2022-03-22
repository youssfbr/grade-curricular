package com.github.youssfbr.gradecurricular.repository;

import com.github.youssfbr.gradecurricular.entity.MateriaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMaterialRepository extends JpaRepository<MateriaEntity, Long> {
}

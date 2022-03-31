package com.github.youssfbr.gradecurricular.repository;

import com.github.youssfbr.gradecurricular.entity.CursoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ICursoRepository extends JpaRepository<CursoEntity, Long> {

    @Query("select c from CursoEntity c where c.codigo = :codigo")
    CursoEntity findCursoByCodigo(@Param("codigo") String codigo);

}

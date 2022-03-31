package com.github.youssfbr.gradecurricular.repository;

import com.github.youssfbr.gradecurricular.entity.MateriaEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IMateriaRepository extends JpaRepository<MateriaEntity, Long> {

    @Query( "select m from MateriaEntity m where m.horas >= :horaMinima" )
    List<MateriaEntity> findByHoraMinima( @Param("horaMinima") int horaMinima );

    @Query("select m from MateriaEntity m where m.frequencia = :frequencia")
    List<MateriaEntity> findByFrequencia(@Param("frequencia")int frequencia);

    @Query("select m from MateriaEntity m where m.codigo = :codigo")
    public MateriaEntity findByCodigo(@Param("codigo")String codigo);
}

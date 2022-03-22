package com.github.youssfbr.gradecurricular.service;

import com.github.youssfbr.gradecurricular.entity.MateriaEntity;
import com.github.youssfbr.gradecurricular.exception.MateriaException;
import com.github.youssfbr.gradecurricular.repository.IMaterialRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class MateriaService implements IMateriaService {

    private final IMaterialRepository materialRepository;

    @Override
    public List<MateriaEntity> listarMateriais() {
        return materialRepository.findAll();
    }

    @Override
    public MateriaEntity consultarMateria(Long id) {
        try {
            return materialRepository
                    .findById(id)
                    .orElseThrow(() ->  new MateriaException("Matéria não encontrada", HttpStatus.NOT_FOUND));
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw new MateriaException("Erro interno identificado. Contate o suporte.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrarMateria(MateriaEntity materia) {
        try {
            materialRepository.save(materia);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean atualizarMateria(MateriaEntity materia) {
        try {
            this.consultarMateria(materia.getId());
            final Optional<MateriaEntity> materiaOptional = materialRepository.findById(materia.getId());

            if (materiaOptional.isPresent()) {
                MateriaEntity materiaEntityAtualizada = materiaOptional.get();

                materiaEntityAtualizada.setNome(materia.getNome());
                materiaEntityAtualizada.setCodigo(materia.getCodigo());
                materiaEntityAtualizada.setHoras(materia.getHoras());
                materiaEntityAtualizada.setFrequencia(materia.getFrequencia());

                materialRepository.save(materiaEntityAtualizada);
            }
            return true;
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Boolean excluirMateria(Long id) {
        try {
            this.consultarMateria(id);
            materialRepository.deleteById(id);
            return true;
        }
        catch (MateriaException m) {
            throw m;
        }
        catch (Exception e) {
            throw e;
        }
    }
}

package com.example.PrimerParcialProgramacion.services;

import com.example.PrimerParcialProgramacion.entities.Dna;
import com.example.PrimerParcialProgramacion.repositories.DnaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DnaService implements BaseService<Dna>{

    //@Autowired
    private DnaRepository dnaRepository;

    public DnaService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }
/*
    @Override
    @Transactional
    public List<Dna> findAll() throws Exception {
        try{
        List<Dna> entities = dnaRepository.findAll();
        return entities;
        } catch (Exception e){
            throw new Exception((e.getMessage()));
        }
    }

    @Transactional
    @Override
    public Dna findById(Long id) throws Exception {
        try{
            Optional<Dna> entityOptional = dnaRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception((e.getMessage()));
        }
    }
*/
    @Transactional
    @Override
    public Dna save(Dna entity) throws Exception {
        try{
            entity = dnaRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception((e.getMessage()));

        }
    }
/*
    @Transactional
    @Override
    public Dna update(Long id, Dna entity) throws Exception {
        try{
            Optional<Dna> entityOptional = dnaRepository.findById(id);
            Dna dna = entityOptional.get();
            dna = dnaRepository.save(dna);
            return dna;
        } catch (Exception e){
            throw new Exception((e.getMessage()));

        }
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws Exception {
        try{
            if (dnaRepository.existsById(id)){
                dnaRepository.deleteById(id);
                return  true;
            }else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception((e.getMessage()));

        }
    }

 */
}

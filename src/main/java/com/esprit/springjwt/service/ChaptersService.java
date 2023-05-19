package com.esprit.springjwt.service;


import com.esprit.springjwt.entity.Categorie;
import com.esprit.springjwt.entity.Chapters;
import com.esprit.springjwt.entity.Formation;
import com.esprit.springjwt.entity.Training;
import com.esprit.springjwt.repository.ChaptersRepository;
import com.esprit.springjwt.repository.FormationRepository;
import com.esprit.springjwt.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChaptersService {
    @Autowired
    private ChaptersRepository ChaptersRepository;
    @Autowired
    private FormationRepository trainingRepository;

    public Chapters addChapters(Chapters Chapters) {

        Formation formation = trainingRepository.findById(Chapters.getFormation().getId()).get();
        Chapters.setFormation(formation);
        return ChaptersRepository.save(Chapters);
    }

    public List<Chapters> getAllChapters() {
        return ChaptersRepository.findAll();
    }

    public void deleteChaptersById(Long id) {
        ChaptersRepository.deleteById(id);
    }

//updqte chapters with checking if the training exist
    public Chapters updateChapters(Chapters Chapters) {
        Formation formation = trainingRepository.findById(Chapters.getFormation().getId()).get();
        Chapters.setFormation(formation);
        return ChaptersRepository.save(Chapters);
    }

    public Chapters getChaptersById(Long id) {
        return ChaptersRepository.findById(id).get();
    }

    public List<Formation> getAllTraining() {
        return trainingRepository.findAll();
    }
}





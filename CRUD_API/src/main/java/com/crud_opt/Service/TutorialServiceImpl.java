package com.crud_opt.Service;

import com.crud_opt.dto.TutorialDTO;
import com.crud_opt.entity.Tutorial;
import com.crud_opt.repository.TutorialRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TutorialServiceImpl implements TutorialService {

    @Autowired
    private TutorialRepository tutorialRepository;

    @Override
    public List<Tutorial> getAllTutorials() {
//        List<Tutorial> tutorialsList = tutorialRepository.findAll();
        return tutorialRepository.findAll();
    }

    @Override
    public Tutorial findTutorialById(Long id) throws Exception {
        return tutorialRepository.findById(id).
                orElseThrow(()-> new Exception("Credentials not found"));
    }

    public Tutorial createTutorial(TutorialDTO tutorialDTO) {
        Tutorial tutorial = new Tutorial();
        tutorial= new ObjectMapper().convertValue(tutorialDTO, Tutorial.class);
         return tutorialRepository.save(tutorial);
    }

    public Tutorial updateTutorialDetails(Long id, TutorialDTO tutorialDTO) throws Exception {

            Tutorial tutorial = tutorialRepository.findById(id)
                    .orElseThrow(() -> new Exception("Tutorial not found"));
            Tutorial updation= new ObjectMapper().convertValue(tutorialDTO, Tutorial.class);
        updation.setId(tutorial.getId());
            return tutorialRepository.save(updation);

    }


    @Override
    public void deleteTutorialById(Long id) throws Exception {

        tutorialRepository.findById(id).orElseThrow(()-> new Exception("Tutorials not found"));
        tutorialRepository.deleteById(id);
        System.out.println("Tutorials deleted successfully");

    }

//
}

package com.crud_opt.Service;

import com.crud_opt.dto.TutorialDTO;
import com.crud_opt.entity.Tutorial;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface TutorialService {

    List<Tutorial> getAllTutorials();

    Tutorial findTutorialById(Long id) throws Exception;

    public void deleteTutorialById(Long id) throws Exception;

    public Tutorial updateTutorialDetails(Long id,TutorialDTO tutorialDTO) throws Exception;

    public Tutorial createTutorial(TutorialDTO tutorial) throws ChangeSetPersister.NotFoundException;
}

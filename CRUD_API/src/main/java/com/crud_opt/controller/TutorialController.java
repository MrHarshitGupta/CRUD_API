package com.crud_opt.controller;

import com.crud_opt.dto.TutorialDTO;
import com.crud_opt.entity.Tutorial;
import com.crud_opt.Service.TutorialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crud/api")
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials() {
        try {
            List<Tutorial> tutorials = tutorialService.getAllTutorials();
            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") Long id) throws Exception {
        Tutorial tutorial = tutorialService.findTutorialById(id);
        if (tutorial != null) {
            return new ResponseEntity<>(tutorial, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials/db")
    public ResponseEntity<?> createTutorial(@Valid @RequestBody TutorialDTO tutorial) {
          try {
            tutorialService.createTutorial(tutorial);
            return ResponseEntity.ok(tutorial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred while saving tutorial.");
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Object> updateTutorials(@PathVariable(value="id") Long id, @RequestBody TutorialDTO updatedTutorial) {

        try {
            Tutorial updateTutorial = tutorialService.updateTutorialDetails(id, updatedTutorial);
            return ResponseEntity.ok(updateTutorial);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error occurred while updating tutorial.");
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable(value="id") Long tutorialId) {
        try {
            tutorialService.deleteTutorialById(tutorialId);
            return new ResponseEntity<>("Tutorial deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Tutorial not deleted", HttpStatus.BAD_REQUEST);
        }
    }
}

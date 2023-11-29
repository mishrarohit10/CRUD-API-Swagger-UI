package com.rohit.CRUDAPI.controllers;
import com.rohit.CRUDAPI.models.Tutorial;
import com.rohit.CRUDAPI.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TutorialController {
    @Autowired
    TutorialRepository tutorialRepo;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) {
            tutorials.addAll(tutorialRepo.findAll());
        } else {
            tutorials.addAll(tutorialRepo.findByTitleContaining(title));
        }

        if(tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tutorials, HttpStatus.OK);
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
        Tutorial _tutorial = tutorialRepo.save(new Tutorial(tutorial.getTitle(),tutorial.getDescription(),tutorial.isPublished()));
        return new ResponseEntity<>(_tutorial,HttpStatus.CREATED);
    }
}

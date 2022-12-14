package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend.utils.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/flashcard")
public class FlashcardController {

    private final FileReader fileReader;

    @Autowired
    public FlashcardController(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Translation>> getWords()  {
        List<Translation> allWords = Collections.EMPTY_LIST;

        try {
            allWords = fileReader.ReadWords();
            return new ResponseEntity(allWords, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/random")
    public ResponseEntity<Word> getRandomWord() {
        List<Translation> allWords = Collections.EMPTY_LIST;

        try {
            allWords = fileReader.ReadWords();

            var r = new Random();
            int low = 0;
            int high = allWords.size() - 1;
            int randomIndex = r.nextInt(high-low) + low;
            var randomSpanishWord = allWords.get(randomIndex).getSpanish();

            return new ResponseEntity(randomSpanishWord, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/submit")
    public ResponseEntity submitTranslationAttempt(@RequestBody Translation submission) {
        List<Translation> allWords = Collections.EMPTY_LIST;

        try {
            allWords = fileReader.ReadWords();
            var isMatch= allWords.stream().anyMatch(x -> x.equals(submission));
            var correctAnswer = allWords.stream().filter(x -> x.getSpanish().equals(submission.getSpanish())).findFirst().orElseThrow();
            return new ResponseEntity(correctAnswer, isMatch ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
        }
        catch(Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

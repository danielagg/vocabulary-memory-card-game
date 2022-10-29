package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/flashcard")
public class FlashcardController {

    @GetMapping("/all")
    public ResponseEntity<List<Translation>> getWords()  {
        List<Translation> allWords = Collections.EMPTY_LIST;

        try {
            allWords = ReadWords();
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
            allWords = ReadWords();

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

    private List<Translation> ReadWords() throws IOException {
        var staticDataResource = new ClassPathResource("words_es_en.json");
        var serializedWords = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(serializedWords, new TypeReference<>() {});
    }
}

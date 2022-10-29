package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class Word {
    private String Word;

    public Word(String word) {
        Word = word;
    }

    //    private String English;
//    private String Spanish;
//
//    public Word(
//            @JsonProperty("english") String english,
//            @JsonProperty("spanish") String spanish) {
//        English = english;
//        Spanish = spanish;
//    }
}
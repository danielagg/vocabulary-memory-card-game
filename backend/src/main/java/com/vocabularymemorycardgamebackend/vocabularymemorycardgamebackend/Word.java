package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class Word {
    private String Word;

    public Word(String word) {
        Word = word;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Word)) {
            return false;
        }

        var parsedOther = (Word) other;

        return parsedOther.getWord().equals(this.getWord());
    }
}
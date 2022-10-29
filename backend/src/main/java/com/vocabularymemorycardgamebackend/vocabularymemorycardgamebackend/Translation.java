package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class Translation {

    private Word English;
    private Word Spanish;

    public Translation(
            @JsonProperty("english") Word english,
            @JsonProperty("spanish") Word spanish) {
        English = english;
        Spanish = spanish;
    }
}

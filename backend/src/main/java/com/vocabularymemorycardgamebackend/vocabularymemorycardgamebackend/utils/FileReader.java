package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend.utils;

import com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend.Translation;

import java.io.IOException;
import java.util.List;

public interface FileReader {
    List<Translation> ReadWords() throws IOException;

}

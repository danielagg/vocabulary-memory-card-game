package com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocabularymemorycardgamebackend.vocabularymemorycardgamebackend.Translation;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TranslationWordListReader implements FileReader {
    public List<Translation> ReadWords() throws IOException {
        var staticDataResource = new ClassPathResource("words_es_en.json");
        var serializedWords = IOUtils.toString(staticDataResource.getInputStream(), StandardCharsets.UTF_8);

        var objectMapper = new ObjectMapper();
        return objectMapper.readValue(serializedWords, new TypeReference<>() {});
    }
}

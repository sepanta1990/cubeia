package com.cubeia.exercise.exercise.controller;

import com.cubeia.exercise.exercise.entity.Translation;
import com.cubeia.exercise.exercise.service.TranslationService;
import com.cubeia.exercise.exercise.util.mapper.TranslationMapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Mohammad Fathizadeh 2020-08-03
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TranslationController.class)
public class TranslationControllerTest {
    private final TranslationMapper translationMapper = Mappers.getMapper(TranslationMapper.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeClass
    public static void setup() {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TranslationService translationService;

    @Test
    public void getTranslationReturnOK() throws Exception {

        String languageCode = "en";
        String key = "buy-chips";
        String meaning = "buy chips";
        Translation translation = new Translation(1, key, meaning, null);

        when(translationService.getTranslationByKeyAndLanguageCode(key, languageCode)).thenReturn(Optional.of(translation));
        MvcResult requestResult = this.mockMvc.perform(get("/translations/search?key=" + key + "&languageCode=" + languageCode)).andExpect(status().isOk()).andReturn();


        com.cubeia.exercise.exercise.dto.Translation responseDto = parseResponse(requestResult, com.cubeia.exercise.exercise.dto.Translation.class);

        assertEquals(responseDto.getKey(), key);
        assertEquals(responseDto.getMeaning(), meaning);
        assertEquals(responseDto.getId(), new Integer(1));
    }

    @Test
    public void getTranslationReturnNotfound() throws Exception {

        String languageCode = "en";
        String key = "buy-chips";
        String meaning = "buy chips";

        when(translationService.getTranslationByKeyAndLanguageCode(key, languageCode)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/translations/search?key=" + key + "&languageCode=" + languageCode)).andExpect(status().isNotFound()).andReturn();

        verify(translationService, times(1)).getTranslationByKeyAndLanguageCode(key, languageCode);

    }

    public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
        try {
            String contentAsString = result.getResponse().getContentAsString();
            return mapper.readValue(contentAsString, responseClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package com.cubeia.exercise.exercise.controller;

import com.cubeia.exercise.exercise.entity.Language;
import com.cubeia.exercise.exercise.service.LanguageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Mohammad Fathizadeh 2020-08-03
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LanguageController.class)
public class LanguageControllerTest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LanguageService languageService;


    @Test
    public void addLanguageShouldReturnOK() throws Exception {

        com.cubeia.exercise.exercise.dto.Language requestDto = new com.cubeia.exercise.exercise.dto.Language(null, "en", "english");
        when(languageService.addLanguage(requestDto)).thenReturn(new Language(1, "en", "english", null));
        MvcResult requestResult = this.mockMvc.perform(post("/languages/").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDto))).andExpect(status().isOk()).andReturn();

        com.cubeia.exercise.exercise.dto.Language responseDto = parseResponse(requestResult, com.cubeia.exercise.exercise.dto.Language.class);
        assertEquals(responseDto.getName(), requestDto.getName());
        assertEquals(responseDto.getCode(), requestDto.getCode());
        assertEquals(responseDto.getId(), new Integer(1));
    }

    @Test
    public void addLanguageShouldReturnConflict() throws Exception {

        com.cubeia.exercise.exercise.dto.Language requestDto = new com.cubeia.exercise.exercise.dto.Language(null, "en", "english");

        when(languageService.addLanguage(requestDto)).thenReturn(null);
        this.mockMvc.perform(post("/languages/").contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDto))).andExpect(status().isConflict());
        verify(languageService, times(1)).addLanguage(requestDto);
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

package io.lpamintuan.springmultipartupload.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import io.lpamintuan.springmultipartupload.services.UploadService;

@ActiveProfiles("test")
@WebMvcTest(UploadController.class)
public class UploadControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UploadService uploadService;

    @Test
    public void uploadShouldBeSuccessfulWhenServiceDoesNotThrowException() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile("file",
                "hello.txt", MediaType.MULTIPART_FORM_DATA_VALUE, "Hello, World!".getBytes());

        mockMvc.perform(multipart("/upload").file(mockFile))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("Successfully uploaded."))
                .andExpect(jsonPath("$.fileName").value(mockFile.getOriginalFilename()));

        verify(uploadService).processUploadFile(any());

    }

    @Test
    public void uploadShouldReturnErrorIfMaxFilesizeExceedsFiveMB() throws Exception {

        byte[] filebyte = new byte[6291456];
        MockMultipartFile mockFile = new MockMultipartFile("file",
                "hello.txt", MediaType.MULTIPART_FORM_DATA_VALUE, filebyte);

        mockMvc.perform(multipart("/upload").file(mockFile))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("File size exceeded."));

        verify(uploadService, never()).processUploadFile(any());

    }

}

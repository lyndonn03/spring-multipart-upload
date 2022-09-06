package io.lpamintuan.springmultipartupload.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import io.lpamintuan.springmultipartupload.entities.UploadFileDetails;
import io.lpamintuan.springmultipartupload.repositories.UploadFileRepository;

import static org.mockito.BDDMockito.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {

    UploadService uploadService;

    @Mock
    UploadFileRepository uploadFileRepository;

    @BeforeEach
    public void setUpEachTest() {
        this.uploadService = new UploadService(uploadFileRepository);
    }

    @Test
    public void processUploadFileSavesTheFileToDB() throws IOException {

        MockMultipartFile mockFile = new MockMultipartFile("file", 
                "hello.txt", MediaType.MULTIPART_FORM_DATA_VALUE, "Hello, World!".getBytes());
                
        UploadFileDetails fileDetails = new UploadFileDetails();
        fileDetails.setFileName("fileName.txt");

        ArgumentCaptor<UploadFileDetails> captor = ArgumentCaptor.forClass(UploadFileDetails.class);

        given(uploadFileRepository.save(any()))
            .willReturn(fileDetails);

        this.uploadService.processUploadFile(mockFile);

        verify(uploadFileRepository).save(captor.capture());
        assertThat(captor.getValue().getFileName()).isEqualTo("hello.txt");
        assertThat(captor.getValue().getByteContent()).isNotNull();

    }

}

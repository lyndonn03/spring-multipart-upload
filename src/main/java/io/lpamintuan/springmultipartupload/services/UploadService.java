package io.lpamintuan.springmultipartupload.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.lpamintuan.springmultipartupload.entities.UploadFileDetails;
import io.lpamintuan.springmultipartupload.repositories.UploadFileRepository;

@Service
public class UploadService {

    UploadFileRepository uploadFileRepository;

    public UploadService(UploadFileRepository repository) {
        this.uploadFileRepository = repository;
    }

    public void processUploadFile(MultipartFile file) throws IOException {

        UploadFileDetails fileDetails = new UploadFileDetails();

        fileDetails.setFileName(file.getOriginalFilename());
        fileDetails.setByteContent(file.getBytes());

        uploadFileRepository.save(fileDetails);

    }


    
}

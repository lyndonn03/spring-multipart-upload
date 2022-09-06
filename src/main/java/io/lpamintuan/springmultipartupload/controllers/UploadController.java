package io.lpamintuan.springmultipartupload.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.lpamintuan.springmultipartupload.controllers.validations.annotations.ValidateFileSize;
import io.lpamintuan.springmultipartupload.services.UploadService;

@RestController
@Validated
public class UploadController {

    @Autowired
    UploadService uploadService;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(
            @RequestParam("file") @Valid @ValidateFileSize(size = 5242880) MultipartFile file) throws IOException {

        uploadService.processUploadFile(file);

        HashMap<String, String> resultMessage = new HashMap<>();
        resultMessage.put("message", "Successfully uploaded.");
        resultMessage.put("fileName", file.getOriginalFilename());
        return new ResponseEntity<>(resultMessage, HttpStatus.ACCEPTED);
    }

}

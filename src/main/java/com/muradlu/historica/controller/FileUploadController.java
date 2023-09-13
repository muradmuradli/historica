package com.muradlu.historica.controller;

import com.muradlu.historica.lib.UrlResponse;
import com.muradlu.historica.service.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class FileUploadController {
    private final FileUpload fileUpload;
    @RequestMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/upload")
    public ResponseEntity<UrlResponse> uploadFile(@RequestParam("image")MultipartFile multipartFile,
                                     Model model) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);

        UrlResponse jsonResponse = new UrlResponse(imageURL);

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}

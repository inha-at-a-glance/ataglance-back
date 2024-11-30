package com.AtaGlance.controller;

import com.AtaGlance.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/api/aws")
@RequiredArgsConstructor
public class S3FileController {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("images") MultipartFile file) {
        /*if (files.length > 15) {
            return ResponseEntity.badRequest().body("Error: Cannot upload more than 15 files at a time.");
        }
        for (MultipartFile file : files) {
            try {
                s3Service.uploadFile(file);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("File upload failed: " + e.getMessage());
            }
        }*/
        try {
            s3Service.uploadFile(file);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("File upload failed: " + e.getMessage());
        }
        return ResponseEntity.ok("Files uploaded successfully");
    }

    @GetMapping("/viewImage")
    public ModelAndView viewImage(@RequestParam("fileName") String fileName) {
        String imageUrl = s3Service.getPresignedUrl(fileName);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("viewImage");
        mav.addObject("imageUrl", imageUrl);
        return mav;
    }

}

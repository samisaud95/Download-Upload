package com.example.serviceUploadeDownloadFile.controller;

import com.example.serviceUploadeDownloadFile.service.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileStorageService fileStorageService;
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
      return ResponseEntity.ok(fileStorageService.upload(file));
    }
    @GetMapping("/download")
    public @ResponseBody ResponseEntity<byte[]> download(
            @RequestParam String fileName,
            HttpServletResponse response) throws IOException {
        return ResponseEntity.ok(fileStorageService.download(fileName, response));
    }

}

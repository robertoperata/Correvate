package com.correvate.zipdemo.controller;

import com.correvate.zipdemo.service.ZipService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FilesController {

    private final ZipService zipService;

    public FilesController(ZipService zipService) {

        this.zipService = zipService;
    }

    @PostMapping(path = "zip-files")
    public ResponseEntity<byte[]> uploadFiles(@RequestParam("files") MultipartFile[] files)
        throws IOException {

        byte[] outputStream = zipService.zipFiles(files);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/zip");
        httpHeaders.add(HttpHeaders.CONTENT_LENGTH, String.valueOf(outputStream.length));
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=files.zip");
        return ResponseEntity.ok().headers(httpHeaders).body(outputStream);

    }
}

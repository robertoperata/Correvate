package com.correvate.zipdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@Slf4j
public class ZipService {

    /**
     * Create a zip file from a list of multipart files
     * @param files
     * @return byte[]
     * @throws IOException
     */
    public byte[] zipFiles(MultipartFile[] files)
        throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        Set<String> uniqueNames = new HashSet<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();

            String uniqueFilename = generateUniqueName(uniqueNames, filename);

            addMultipartFileToZipWithFilename(file, zipOutputStream, uniqueFilename);
            log.info("zipping file: " + filename);
        }

        zipOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private void addMultipartFileToZipWithFilename(MultipartFile file, ZipOutputStream zipOutputStream, String filename)
        throws IOException {

        InputStream inputStream = file.getInputStream();
        ZipEntry zipEntry = new ZipEntry(filename);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while((length = inputStream.read(bytes)) >= 0) {
            zipOutputStream.write(bytes, 0, length);
        }
    }

    private String generateUniqueName(Set<String> uniqueNames, String filename) {

        if(!uniqueNames.add(filename)) {
            int postfix = 1;
            while(!uniqueNames.add(filename + "_" + postfix)) {
                postfix++;
            }
            return filename + "_" + postfix;
        }
        return filename;
    }

}

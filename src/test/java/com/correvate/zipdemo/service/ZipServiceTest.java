package com.correvate.zipdemo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ZipServiceTest {

    @InjectMocks
    private ZipService zipService;

    @Test
    @DisplayName("when zipFile more file with same name should zip them")
    void zipFile_with_same_name()
        throws IOException {

        MockMultipartFile file1 = new MockMultipartFile("file", "test".getBytes());
        MockMultipartFile file2 = new MockMultipartFile("file", "test".getBytes());
        byte[] bytes = zipService.zipFiles(new MultipartFile[] {file1,file2});
        Assertions.assertThat(bytes.length).isGreaterThan(0);
    }

}
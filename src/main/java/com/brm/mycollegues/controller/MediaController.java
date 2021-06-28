package com.brm.mycollegues.controller;

import com.brm.mycollegues.config.AWSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
public class MediaController {

    @Autowired
    private AWSConfig config;

    @PostMapping("upload")
    public String uploadFile(@RequestParam(value = "file") MultipartFile[] file) {
        Arrays.asList(file).stream().forEach(multipartFile -> config.uploadFile(multipartFile));
        return  "File uploaded";
    }

    @GetMapping("generateurl/{fileName}")
    public String downloadFile(@PathVariable String fileName) {
        String mediaUrl= "https://test_box.hb.bizmrg.com/test/"+fileName;
       return mediaUrl;
    }
}
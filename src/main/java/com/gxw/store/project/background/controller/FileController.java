package com.gxw.store.project.background.controller;

import com.gxw.store.project.common.interceptor.NeedToken;
import com.gxw.store.project.common.utils.ResponseResult;
import com.gxw.store.project.file.config.Constants;
import com.gxw.store.project.file.config.StorageType;
import com.gxw.store.project.file.service.FileService;
import com.gxw.store.project.file.utils.MimeTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @NeedToken
    @PostMapping("/images/localUpload")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.setCategory(Constants.IMAGES_CATEGORY);
        HashMap<String, String> res = fileService.upload(file, StorageType.LOCAL, MimeTypeUtils.IMAGE_EXTENSION);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PostMapping("/images/aliYunUpload")
    public ResponseResult uploadAliOss(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.setCategory(Constants.IMAGES_CATEGORY);
        HashMap<String, String> res = fileService.upload(file, StorageType.ALIYUN, MimeTypeUtils.IMAGE_EXTENSION);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PostMapping("/doc/localUpload")
    public ResponseResult uploadDoc(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.setCategory(Constants.DOC_CATEGORY);
        HashMap<String, String> res = fileService.upload(file, StorageType.LOCAL, MimeTypeUtils.DOC_EXTENSION);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PostMapping("/doc/aliYunUpload")
    public ResponseResult uploadDocAliOss(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.setCategory(Constants.DOC_CATEGORY);
        HashMap<String, String> res = fileService.upload(file, StorageType.ALIYUN, MimeTypeUtils.DOC_EXTENSION);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PostMapping("/media/localUpload")
    public ResponseResult uploadMedia(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.setCategory(Constants.MEDIA_CATEGORY);
        HashMap<String, String> res =fileService.upload(file, StorageType.LOCAL, MimeTypeUtils.MEDIA_EXTENSION);
        return ResponseResult.success(res);
    }

    @NeedToken
    @PostMapping("/media/aliYunUpload")
    public ResponseResult uploadMediaAliOss(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.setCategory(Constants.MEDIA_CATEGORY);
        HashMap<String, String> res =fileService.upload(file, StorageType.ALIYUN, MimeTypeUtils.MEDIA_EXTENSION);
        return ResponseResult.success(res);
    }

}

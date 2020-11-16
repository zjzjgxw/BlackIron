package com.gxw.store.project.file.service;

import com.gxw.store.project.common.utils.DateUtils;
import com.gxw.store.project.common.utils.Md5Utils;
import com.gxw.store.project.common.utils.ServletUtils;
import com.gxw.store.project.common.utils.exception.FileNameLengthLimitExceededException;
import com.gxw.store.project.common.utils.exception.InvalidFileTypeException;
import com.gxw.store.project.file.config.Constants;
import com.gxw.store.project.file.config.StorageType;
import com.gxw.store.project.file.utils.AliyunOss;
import com.gxw.store.project.file.utils.MimeTypeUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Service
public class FileService {
    @Value("${file.root.dir}")
    private String rootDir;

    /**
     * 类目
     * (不能已/ 开头和结尾,用于给文件进行分类，例如图片放入images,文档类放入doc）
     */
    private String category = "";
    /**
     * 文件名最大长度
     */
    private static final int MAX_FILENAME_LENGTH = 200;

    @Autowired
    private AliyunOss aliyunOss;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 上传
     * @param file
     * @param storageType
     * @return
     * @throws IOException
     */
    public HashMap<String,String> upload(MultipartFile file, StorageType storageType, String[] allowedExtension) throws IOException {
        HashMap<String,String> res = new HashMap<>();
        switch (storageType){
            case LOCAL:
                String path = upload(rootDir + "/" + category,file,allowedExtension);
                String url = ServletUtils.getUrl() + Constants.RESOURCE_PREFIX + "/" + path;
                res.put("path",path);
                res.put("url",url);
                break;
            case ALIYUN:
                res = uploadAliYunOss(file,allowedExtension);
                break;
            case QINIU:break;
            default:
                break;
        }
        return res;
    }


    /**
     *
     * @param file
     * @param allowedExtension
     * @return
     * @throws IOException
     */
    private HashMap<String,String> uploadAliYunOss(MultipartFile file, String[] allowedExtension) throws IOException {
        File desc = saveFile(rootDir + "/" + category, file, allowedExtension);
        if (desc.exists()) {
            aliyunOss.setBaseDir(category);
            String key = aliyunOss.uploadFile(desc);
            String url = aliyunOss.getUrl(key);
            String snapshotUrl = aliyunOss.getSnapshotUrl(key);
            HashMap<String,String> res = new HashMap<>();
            res.put("path",key);
            res.put("url",url);
            res.put("snapshot",snapshotUrl);
            desc.delete();
            return res;
        }
        return null;
    }

    /**
     * @param baseDir          文件上传目录
     * @param file             文件
     * @param allowedExtension 允许文件类型
     * @return
     */
    private String upload(String baseDir, MultipartFile file, String[] allowedExtension) throws IOException {
        File desc = saveFile(baseDir, file, allowedExtension);
        return getPathFileName(desc.getAbsolutePath());
    }


    private String getPathFileName(String fileName){
        int dirLastIndex = rootDir.length() + 1;
        return fileName.substring(dirLastIndex);
    }


    /**
     *  将文件保存到本地
     * @param baseDir
     * @param file
     * @param allowedExtension
     * @return
     * @throws IOException
     */
    private File saveFile(String baseDir, MultipartFile file, String[] allowedExtension) throws IOException {
        int fileNameLength = file.getOriginalFilename().length();
        if (fileNameLength > MAX_FILENAME_LENGTH) {
            throw new FileNameLengthLimitExceededException();
        }
        assertAllowed(file, allowedExtension);
        String fileName = extractFilename(file); //格式化文件名，防止文件冲突，并放入对应日期的文件夹中
        File desc = getAbsoluteFile(baseDir, fileName);
        file.transferTo(desc);
        return desc;
    }


    /**
     * 获取文件绝对路径
     *
     * @param uploadDir
     * @param fileName
     * @return
     * @throws IOException
     */
    private File getAbsoluteFile(String uploadDir, String fileName) throws IOException {
        File desc = new File(uploadDir + File.separator + fileName);

        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        if (!desc.exists()) {
            desc.createNewFile();
        }
        return desc;
    }

    /**
     * 编码文件名
     */
    private String extractFilename(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String extension = getExtension(file);
        fileName = DateUtils.datePath() + "/" + encodingFilename(fileName) + "." + extension;
        return fileName;
    }

    /**
     * 编码文件名
     */
    private String encodingFilename(String fileName) {
        fileName = fileName.replace("_", " ");
        fileName = Md5Utils.hash(fileName + System.nanoTime());
        return fileName;
    }


    /**
     * 判断文件类型是否符合
     *
     * @param file
     * @param allowedExtension
     */
    private void assertAllowed(MultipartFile file, String[] allowedExtension) {
        String extension = getExtension(file);
        if (allowedExtension != null) {
            if (!isAllowedExtension(extension, allowedExtension)) { //文件类型不符合抛出异常
                throw new InvalidFileTypeException();
            }
        }
    }

    private boolean isAllowedExtension(String extension, String[] allowedExtension) {
        for (String str : allowedExtension) {
            if (str.equalsIgnoreCase(extension)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    private String getExtension(MultipartFile file) {
        //先依文件名拿去后缀
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (StringUtils.isEmpty(extension)) { //如果为空，使用文件类型得到后缀
            extension = MimeTypeUtils.getExtension(file.getContentType());
        }
        return extension;
    }
}

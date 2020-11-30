package com.gxw.store.project.common.utils;

import com.gxw.store.project.file.config.Constants;

public class FileUtils {

    private static int storeType = 1; //存储方式 1为本地，2为oss

    /**
     * 更具情况返回文件访问路径
     *
     * @param url
     * @return
     */
    public static String getPath(String url) {
        if (storeType == 1) {
            return "http://" + ServletUtils.getRequest().getServerName() + ":" + ServletUtils.getRequest().getServerPort() + Constants.RESOURCE_PREFIX + "/" + url;
        }
        return url;
    }

    /**
     * 判断是否已经是全路径
     * @param url
     * @return
     */
    public static boolean isFullPath(String url){
        return url.startsWith("http://") || url.startsWith("https://");
    }
}

package com.gxw.store.project.common.utils;

public class FileUtils {

    private static int storeType = 1; //存储方式 1为本地，2为oss

    /**
     * 更具情况返回文件访问路径
     * @param url
     * @return
     */
    public static String getPath(String url){
        if(storeType == 1){
            return ServletUtils.getRequest().getServerName()+"/"+url;
        }
        return url;
    }
}

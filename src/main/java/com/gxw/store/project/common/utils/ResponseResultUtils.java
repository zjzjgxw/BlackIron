package com.gxw.store.project.common.utils;


import com.gxw.store.project.common.utils.constants.HttpStatus;

public class ResponseResultUtils {
    public static boolean isSuccess(ResponseResult res){
        Integer code = (Integer) res.get(ResponseResult.CODE_TAG);
        if(code.equals(HttpStatus.SUCCESS)){
            return true;
        }
        return false;
    }
}

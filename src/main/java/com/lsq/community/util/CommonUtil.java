package com.lsq.community.util;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    /**
     * 获取用户请求ip
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }


    /**
     * 获取当前时间用户名
     * @return
     */
    public static String getCurrentDateFileName(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Random random = new Random();
        return simpleDateFormat.format(date) + random.nextInt(100);
    }

    /**
     * 根据数字获取用户性别
     * @param gender
     * @return
     */
    public static String getUserGenderString(int gender){
        if(gender == 1) return "男";
        else if(gender == 2) return "女";
        else return "保密";
    }
}

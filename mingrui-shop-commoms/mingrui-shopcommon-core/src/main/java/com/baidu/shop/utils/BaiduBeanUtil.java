package com.baidu.shop.utils;

import org.springframework.beans.BeanUtils;

public class BaiduBeanUtil<T1,T2> {

    public static <T2> T2 copyProperties(Object source,Class<T2> clazz){

        if(null == source) return null;

        if(null == clazz) return null;

        try {
            //创建当前类型的实例
            T2 t2 = clazz.newInstance();
            BeanUtils.copyProperties(source,t2);
            return t2;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

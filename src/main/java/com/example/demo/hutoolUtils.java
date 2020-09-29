package com.example.demo;

import cn.hutool.core.util.StrUtil;

public class hutoolUtils {

    public static void main(String[] args) {
        System.out.println( StrUtil.format("a{}c{}e", "b","d"));
    }
}

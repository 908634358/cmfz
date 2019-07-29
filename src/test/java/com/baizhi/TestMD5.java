package com.baizhi;

import org.apache.shiro.crypto.hash.Md5Hash;

public class TestMD5 {

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456", "abcd");
        String s = md5Hash.toHex();
        System.out.println(s);


//        e10adc3949ba59abbe56e057f20f883e      123456
//        da3177cbd9f064004b6a0d59a3a484bb      123456+abcd
//        68609b8b64988c0f4def093eaa025e05      123456+abcd+1024


    }


}

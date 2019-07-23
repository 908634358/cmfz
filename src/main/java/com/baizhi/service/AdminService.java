package com.baizhi.service;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    void findLogin(String code, String username, String password, HttpServletRequest request);

}

package com.huoli.utility.excel;

import com.huoli.domain.User;

import java.util.List;

public interface Excel {

    List<User> readExcelFile(String fileName);

    void createExcelFile(List<User> userList);

}

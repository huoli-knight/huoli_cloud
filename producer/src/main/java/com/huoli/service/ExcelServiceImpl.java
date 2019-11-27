package com.huoli.service;

import com.huoli.domain.User;
import com.huoli.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getExcelDatabase() {
        return userMapper.selectUser();
    }
}

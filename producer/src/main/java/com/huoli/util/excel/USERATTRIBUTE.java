package com.huoli.util.excel;

public enum USERATTRIBUTE {

    id(0, "userId"),

    姓名(1, "userName"),

    邮箱(2, "userEmail"),

    逻辑状态(3, "userState"),

    手机号码(4, "userPhone"),

    用户信息(5, "userMessage"),

    性别(6, "userSex");

    private int index;

    private String attribute;

    USERATTRIBUTE(int index, String attribute) {
        this.index = index;
        this.attribute = attribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public int getIndex(){
        return index;
    }

}

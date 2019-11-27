package com.huoli.utility.excel;

import com.huoli.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserUtil {

    /**
     * 判断是否是User中的属性
     */
    private int[] isAttribute;

    /**
     * 记录对应的位置
     */
    private List<Integer> position;

    public UserUtil() {
        isAttribute = new int[USERATTRIBUTE.values().length];
        Arrays.fill(isAttribute, 0);
        position = new ArrayList<>();
    }

    public boolean isUserAttribute(String attribute) {
        for (USERATTRIBUTE userAttribute : USERATTRIBUTE.values()) {
            if (userAttribute.getAttribute().equals(attribute) || userAttribute.toString().equals(attribute)) {
                if (isAttribute[userAttribute.getIndex()] != 0) {
                    return false;
                }
                isAttribute[userAttribute.getIndex()] = 1;
                position.add(userAttribute.getIndex());
            }
        }
        return true;
    }

    public User getNewUser(List<String> attribute) throws NumberFormatException{
        User result = new User();
        for (int i = 0; i < attribute.size(); i++) {
            setUser(result, i, attribute.get(i));
        }
        return result;
    }

    private void setUser(User user, int i, String context) throws NumberFormatException{
        switch (USERATTRIBUTE.values()[position.get(i)]) {
            case id:
                user.setUserId(Integer.valueOf(context));
                break;
            case 姓名:
                user.setUserName(context);
                break;
            case 性别:
                user.setUserSex(context);
                break;
            case 邮箱:
                user.setUserEmail(context);
                break;
            case 手机号码:
                user.setUserPhone(context);
                break;
            case 用户信息:
                user.setUserMessage(context);
                break;
            case 逻辑状态:
                user.setUserState(Integer.valueOf(context));
                break;
            default:
        }
    }

}

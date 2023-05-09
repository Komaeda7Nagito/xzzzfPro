package com.xzzzf.五月;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {

    public static void copyProperties(Object source, Object target) {

        Map<String, Field> sourceFieldMap = new HashMap<>();
        Map<String, Field> targetFieldMap = new HashMap<>();

        for (Field field : source.getClass().getDeclaredFields()) {
            sourceFieldMap.put(field.getName(), field);
        }

        for (Field field : target.getClass().getDeclaredFields()) {
            targetFieldMap.put(field.getName(), field);
        }

        for (String fieldName : sourceFieldMap.keySet()) {
            if (targetFieldMap.containsKey(fieldName)) {
                Field sourceField = sourceFieldMap.get(fieldName);
                Field targetField = targetFieldMap.get(fieldName);

                if (sourceField.getType().equals(targetField.getType())) {
                    sourceField.setAccessible(true);
                    targetField.setAccessible(true);
                    try {
                        targetField.set(target, sourceField.get(source));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();

        int i = 0;
        while (i <= 1000000) {
            User user = new User();
            user.setName("xzzzf");
            user.setDes("des");
            User user1 = new User();
            copyProperties(user, user1);


            TemporaryUser temporaryUser = new TemporaryUser();
            copyProperties(user, temporaryUser);

            i++;
        }

        System.out.println(System.currentTimeMillis() - l);
    }
}

@Data
class User {
    private String name;

    private String des;

    private Integer age;

    private String address;

    private String phone;


}

@Data
class TemporaryUser {
    private String name;

    private String des;

    private Integer age;

    private String address;

    private String phone;

    private String email;

    private String qq;

    private String wechat;
}

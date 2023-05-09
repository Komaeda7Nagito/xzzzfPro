package com.xzzzf.五月;

import java.lang.reflect.Field;

public class BeanUtils3 {
    public static void copyProperties(Object source, Object target) {

        Field[] sourceDeclaredFields = source.getClass().getDeclaredFields();
        Field[] targetDeclaredFields = target.getClass().getDeclaredFields();

        for (Field sourceDeclaredField : sourceDeclaredFields) {
            for (Field targetDeclaredField : targetDeclaredFields) {
                if (sourceDeclaredField.getName().equals(targetDeclaredField.getName()) && sourceDeclaredField.getType().equals(targetDeclaredField.getType())) {
                    sourceDeclaredField.setAccessible(true);
                    targetDeclaredField.setAccessible(true);
                    try {
                        targetDeclaredField.set(target, sourceDeclaredField.get(source));
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
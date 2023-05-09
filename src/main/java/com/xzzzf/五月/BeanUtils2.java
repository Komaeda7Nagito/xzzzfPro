package com.xzzzf.五月;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils2 {


    public static void copyProperties(Object source, Object target) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor[] sourcePropertyDescriptors = Introspector.getBeanInfo(source.getClass()).getPropertyDescriptors();
        PropertyDescriptor[] targetPropertyDescriptors = Introspector.getBeanInfo(target.getClass()).getPropertyDescriptors();

        Map<String, PropertyDescriptor> sourceFieldMap = new HashMap<>();
        Map<String, PropertyDescriptor> targetFieldMap = new HashMap<>();

        for (PropertyDescriptor propertyDescriptor : sourcePropertyDescriptors) {
            sourceFieldMap.put(propertyDescriptor.getName(), propertyDescriptor);
        }

        for (PropertyDescriptor propertyDescriptor : targetPropertyDescriptors) {
            targetFieldMap.put(propertyDescriptor.getName(), propertyDescriptor);
        }

        for (String fieldName : sourceFieldMap.keySet()) {
            if (targetFieldMap.containsKey(fieldName) && !fieldName.equals("class")) {
                PropertyDescriptor sourcePropertyDescriptor = sourceFieldMap.get(fieldName);
                PropertyDescriptor targetPropertyDescriptor = targetFieldMap.get(fieldName);

                if (sourcePropertyDescriptor.getPropertyType().equals(targetPropertyDescriptor.getPropertyType())) {
                    Method readMethod = sourcePropertyDescriptor.getReadMethod();
                    Method writeMethod = targetPropertyDescriptor.getWriteMethod();

                    Object value = readMethod.invoke(source);

                    writeMethod.invoke(target, value);
                }
            }
        }
    }

    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        long l = System.currentTimeMillis();
        int i = 0;
        while (i <= 1000000){
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


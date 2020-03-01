package com.sio.demo;

import annotation.Component;
import beanfactory.DefaultBeanFactory;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 13:49 2020/3/1
 *@Modified By:
 *
 */
@Component
public class Test {

    void test() {
        System.out.println("instant");
    }

    public static void main(String[] args) {
        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        Test test = (Test) defaultBeanFactory.getBean("test");
        test.test();
    }
}

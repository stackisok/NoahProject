package com.sio.demo;

import annotation.Autowire;
import annotation.Component;
import beanfactory.DefaultBeanFactory;
import context.GenericApplicationContext;
import reader.BeanDefinitionReader;

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

    @Autowire
    Animal animal;


    @Autowire
    Bird bird;
    void test() {
        System.out.println("instant");
    }

    public static void main(String[] args) {



        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        GenericApplicationContext genericApplicationContext = new GenericApplicationContext(defaultBeanFactory);
        Test bean = (Test)genericApplicationContext.getBean("com.sio.demo.Test");
        bean.bird.eat();
        bean.animal.eat();


    }
}

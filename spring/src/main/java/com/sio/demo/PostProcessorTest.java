package com.sio.demo;

import annotation.Component;
import beanfactory.InstantiationAwareBeanPostProcessor;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 23:17 2020/3/7
 *@Modified By:
 *
 */
@Component
public class PostProcessorTest implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        System.out.println("postProcessBeforeInstantiation");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        System.out.println("postProcessAfterInstantiation");

        return false;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }
}

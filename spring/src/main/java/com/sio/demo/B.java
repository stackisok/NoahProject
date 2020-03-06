package com.sio.demo;

import annotation.Autowire;
import annotation.Component;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 22:37 2020/3/5
 *@Modified By:
 *
 */
@Component
public class B {

    @Autowire
    public A a;

    public void say() {
        System.out.println("I'm B");
    }
}

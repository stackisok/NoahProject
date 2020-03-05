package com.sio.demo;

import annotation.Component;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 21:27 2020/3/5
 *@Modified By:
 *
 */
@Component
public class Bird implements Animal {
    @Override
    public void eat() {
        System.out.println("eat");
    }
}

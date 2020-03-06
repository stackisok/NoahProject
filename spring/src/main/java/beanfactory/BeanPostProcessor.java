package beanfactory;

import com.sun.istack.internal.Nullable;

/***
 *
 *@Author ChenjunWang
 *@Description: 后置处理器 在初始化前后
 *@Date: Created in 22:48 2020/3/6
 *@Modified By:
 *
 */
public interface BeanPostProcessor {

    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName)  {
        return bean;
    }
}

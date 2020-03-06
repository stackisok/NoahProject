package beanfactory;

import com.sun.istack.internal.Nullable;

/***
 *
 *@Author ChenjunWang
 *@Description: 后置处理器 在实例化前后
 *@Date: Created in 22:56 2020/3/6`
 *@Modified By:
 *
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    @Nullable
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }

    default boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }

}

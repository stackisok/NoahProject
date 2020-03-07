package beanfactory;

import java.util.List;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 15:31 2020/3/1
 *@Modified By:
 *
 */
public interface BeanFactory {


    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    Object getBean(String beanName);

    <T> T getBean(Class<T> requiredType);

    List<String> getBeanNamesForType(Class<?> type);

    void addBeanPostProcessor(BeanPostProcessor postProcessor);

    Object getBean(String beanName, Class<?> requiredType);

}

package beanfactory;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 15:31 2020/3/1
 *@Modified By:
 *
 */
public interface BeanFactory {

    Object getBean(String beanName);

    <T> T getBean(Class<T> requiredType);
}

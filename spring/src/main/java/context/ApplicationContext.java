package context;

import beanfactory.BeanFactory;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 15:36 2020/3/1
 *@Modified By:
 *
 */
public interface  ApplicationContext extends BeanFactory {
    String getId();

    String getApplicationName();


    BeanFactory getBeanFactory();

    void refresh();

}

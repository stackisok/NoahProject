package context;

import beanfactory.BeanFactory;
import beanfactory.BeanPostProcessor;
import reader.BeanDefinitionReader;

import java.util.List;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 15:41 2020/3/1
 *@Modified By:
 *
 */
public class GenericApplicationContext extends AbstractApplicationContext {

    private BeanFactory beanFactory;


    public GenericApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        refresh();

    }

    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    protected void refreshBeanFactory() {

        //加载beanDefinition


    }

    protected void onRefresh() {

    }

    @Override
    public List<String> getBeanNamesForType(Class<?> type) {
        return beanFactory.getBeanNamesForType(type);
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        beanFactory.addBeanPostProcessor(postProcessor);
    }

    @Override
    public Object getBean(String beanName, Class<?> requiredType) {
        return beanFactory.getBean(beanName, requiredType);
    }
}

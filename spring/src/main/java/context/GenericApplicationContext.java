package context;

import beanfactory.BeanFactory;
import reader.BeanDefinitionReader;

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
    }

    public BeanFactory getBeanFactory() {
        return this.beanFactory;
    }

    protected void refreshBeanFactory() {

        //加载beanDefinition
        loadBeanDefinitions();

    }


    protected void onRefresh() {

    }

    @Override
    public void loadBeanDefinitions() {
        beanFactory.loadBeanDefinitions();


    }
}

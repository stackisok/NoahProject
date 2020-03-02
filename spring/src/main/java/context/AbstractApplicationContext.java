package context;

import beanfactory.BeanFactory;
import reader.BeanDefinitionReader;

import java.util.List;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 15:36 2020/3/1
 *@Modified By:
 *
 */
public abstract class AbstractApplicationContext implements ApplicationContext {




    private String id = "";
    private String applicationName = "";

    private final Object startupShutdownMonitor = new Object();





    public String getId() {
        return this.id;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public Object getBean(String beanName) {
        return getBeanFactory().getBean(beanName);
    }

    public <T> T getBean(Class<T> requiredType) {
        return getBeanFactory().getBean(requiredType);
    }


    public abstract BeanFactory getBeanFactory();


    protected abstract void refreshBeanFactory();

    protected BeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return getBeanFactory();
    }

    public void refresh() {
        synchronized (this.startupShutdownMonitor) {




            //获得一个加载好的工厂
            BeanFactory beanFactory = obtainFreshBeanFactory();



            try {

                onRefresh();

                // Check for listener beans and register them.
//                registerListeners();

                // Instantiate all remaining (non-lazy-init) singletons.
//                finishBeanFactoryInitialization(beanFactory);

                // Last step: publish corresponding event.
//                finishRefresh();
            }


            finally {

                //清理加载过程中的缓存
                resetCommonCaches();
            }
        }
    }

    protected void registryBeanDefinitions(List<String> registryBeanClasses) {

    }

    protected abstract void onRefresh();

    protected void resetCommonCaches() {

    }
}

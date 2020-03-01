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
            // Prepare this context for refreshing.
//            prepareRefresh();

            // Tell the subclass to refresh the internal bean factory.
            BeanFactory beanFactory = obtainFreshBeanFactory();

            // Prepare the bean factory for use in this context.
//            prepareBeanFactory(beanFactory);

            try {
                // Allows post-processing of the bean factory in context subclasses.
//                postProcessBeanFactory(beanFactory);

                // Invoke factory processors registered as beans in the context.
//                invokeBeanFactoryPostProcessors(beanFactory);

                // Register bean processors that intercept bean creation.
//                registerBeanPostProcessors(beanFactory);

                // Initialize message source for this context.
//                initMessageSource();

                // Initialize event multicaster for this context.
//                initApplicationEventMulticaster();

                // Initialize other special beans in specific context subclasses.
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

    protected abstract void onRefresh();

    protected void resetCommonCaches() {

    }
}

package beanfactory;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 16:45 2020/3/1
 *@Modified By:
 *
 */
public class BeanDefinition {
    private String factoryBeanName;
    private boolean lazyInit = false;
    private String beanClassName;
    private String scope = "";

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

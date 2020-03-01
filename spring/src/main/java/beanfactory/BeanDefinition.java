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
    private String beanType;
    private String scope = "";
}

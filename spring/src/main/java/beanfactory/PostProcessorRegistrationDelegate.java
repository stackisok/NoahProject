package beanfactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 22:01 2020/3/7
 *@Modified By:
 *
 */
final class PostProcessorRegistrationDelegate {


    public static void registerBeanPostProcessors(BeanFactory beanFactory) {

        List<String> postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class);
        List<BeanPostProcessor> postProcessors = new LinkedList<>();


        for (String ppName : postProcessorNames) {
            BeanPostProcessor bean = (BeanPostProcessor)beanFactory.getBean(ppName, BeanPostProcessor.class);
            postProcessors.add(bean);

        }
        registerBeanPostProcessors(beanFactory, postProcessors);
    }


    /**
     * Register the given BeanPostProcessor beans.
     */
    private static void registerBeanPostProcessors(
            BeanFactory beanFactory, List<BeanPostProcessor> postProcessors) {

        for (BeanPostProcessor postProcessor : postProcessors) {
            beanFactory.addBeanPostProcessor(postProcessor);
        }
    }

}

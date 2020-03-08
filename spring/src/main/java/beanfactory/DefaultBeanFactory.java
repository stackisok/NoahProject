package beanfactory;

import annotation.Autowire;
import annotation.Component;
import reader.BeanDefinitionReader;

import java.io.File;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 13:43 2020/3/1
 *@Modified By:
 *
 */
public class DefaultBeanFactory implements BeanFactory {

    private BeanDefinitionReader bdr;

    private String [] configLocations = {"application.properties"};
    Map<String, Object> beanMap = new ConcurrentHashMap();
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);
    Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    private final List<BeanPostProcessor> beanPostProcessors = new LinkedList<>();

    String baseDir = "com/sio/demo";

    public DefaultBeanFactory() {


        //加载指定包下面的所有类
        bdr = new BeanDefinitionReader(configLocations);

        //将类注册成beanDefinition
        registryBeanDefinitions(bdr.getRegistryBeanClasses());


        //初始化postProcessor
        PostProcessorRegistrationDelegate.registerBeanPostProcessors(this);

        //初始化所有的类

        initBeans();


//
//
//        //获得要扫描类的文件目录
//        URL systemResource = ClassLoader.getSystemResource(baseDir);
//        File file = new File(systemResource.getPath());
//        String[] list = file.list();
//        //在目录下找到需要给框架容器托管的bean，并加入到容器
//        for (String f : list) {
//
//            String base = baseDir.replace("/", ".");
//            String clazzPath = base + "." +f.replace(".class", "");
//            try {
//                Class<?> clazz = Class.forName(clazzPath);
//
//                if (clazz.isAnnotationPresent(Component.class)) {
//                    Component component = clazz.getAnnotation(Component.class);
//                    String name = component.value();
//                    if (name.equals("")) {
//                        name = clazz.getSimpleName();
//                    }
//                    beanMap.put(lowerFirstCase(name), clazz.newInstance());
//
//                }
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private void initBeans() {
        //实例化
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {

            doGetBean(beanDefinitionEntry.getKey());
        }


        //初始化
        for (Map.Entry<String, Object> bean : beanMap.entrySet()) {
            String beanName = bean.getKey();
            Object value = bean.getValue();

            Field[] fields = value.getClass().getDeclaredFields();

            for (Field field : fields) {
                boolean present = field.isAnnotationPresent(Autowire.class);
                if (present) {
                    Autowire autowire = field.getAnnotation(Autowire.class);

                    //要注入的类型
                    Class<?> clazz = field.getType();
                    String name = clazz.getName();
                    Object o = beanMap.get(name);
                    try {
                        field.setAccessible(true);
                        field.set(value, o);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                }

            }


        }


    }

    private void populateBean() {

    }

    private Object doGetBean(String beanName) {

        Object o = beanMap.get(beanName);
        if (o != null) return o;
        BeanDefinition value = beanDefinitionMap.get(beanName);
        String beanClassName = value.getBeanClassName();
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Object instance = beanMap.get(beanClassName);

            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                    ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(instance.getClass(), beanName);
                }
            }
            if (instance == null) {
                instance = clazz.newInstance();
            }
            beanMap.put(beanName, instance);

            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                    ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessAfterInstantiation(instance, beanName);
                }
            }
            return instance;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Object getBean(String beanName) {
        Object instant = doGetBean(beanName);

        return instant;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return null;
    }

    @Override
    public List<String> getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();

        for (String beanDefinitionName : beanDefinitionNames) {

            if (isTypeMatch(beanDefinitionName, type)) {
                result.add(beanDefinitionName);
            }

        }
        return result;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        //先删除重复的
        beanPostProcessors.remove(postProcessor);
        //添加后置处理器
        beanPostProcessors.add(postProcessor);
    }

    @Override
    public Object getBean(String beanName, Class<?> requiredType) {

        Object o = beanMap.get(beanName);
        if (requiredType != null && !requiredType.isInstance(o)) {

            return o;
        }

        return null;
    }

    private boolean isTypeMatch(String beanDefinitionName, Class<?> type) {

        Object beanInstance = getBean(beanDefinitionName);

        boolean assignableFrom = type.isAssignableFrom(beanInstance.getClass());
        if (assignableFrom) {
            return true;
        }
        return false;

    }

    private void registryBeanDefinitions(List<String> registryBeanClasses) {

        registryBeanClasses.forEach(beanClass ->{
            try {
                Class<?> clazz = Class.forName(beanClass);
                if (clazz.isAnnotationPresent(Component.class)) {
                    Component component = clazz.getAnnotation(Component.class);
                    String name = component.value();
                    if (name == null || name.equals("")) {
                        name = clazz.getName();
                    }

                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setBeanClassName(beanClass);
                    beanDefinition.setFactoryBeanName(lowerFirstCase(clazz.getSimpleName()));
                    beanDefinition.setLazyInit(false);
                    beanDefinition.setScope(BeanFactory.SCOPE_SINGLETON);
                    beanDefinitionMap.put(name, beanDefinition);
                    beanDefinitionNames.add(name);

                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        String simpleName = i.getName();
                        beanDefinitionMap.put(simpleName, beanDefinition);
                        beanDefinitionNames.add(simpleName);

                    }

                } else {

                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}

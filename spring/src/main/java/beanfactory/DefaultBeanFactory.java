package beanfactory;

import annotation.Component;

import java.io.File;
import java.net.URL;
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
public class DefaultBeanFactory {

    Map<String, Object> beanMap = new ConcurrentHashMap<String, Object>();

    String baseDir = "com/sio/demo";

    public DefaultBeanFactory() {

        //获得要扫描类的文件目录
        URL systemResource = ClassLoader.getSystemResource(baseDir);
        File file = new File(systemResource.getPath());
        String[] list = file.list();
        //在目录下找到需要给框架容器托管的bean，并加入到容器
        for (String f : list) {

            String base = baseDir.replace("/", ".");
            String clazzPath = base + "." +f.replace(".class", "");
            try {
                Class<?> clazz = Class.forName(clazzPath);

                if (clazz.isAnnotationPresent(Component.class)) {
                    Component component = clazz.getAnnotation(Component.class);
                    String name = component.value();
                    if (name.equals("")) {
                        name = clazz.getSimpleName();
                    }
                    beanMap.put(lowerFirstCase(name), clazz.newInstance());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        this.beanMap = beanMap;
    }

    public Object getBean(String beanName) {
        Object instant = beanMap.get(beanName);
        if (instant == null) {

        }
        return instant;
    }

    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


}

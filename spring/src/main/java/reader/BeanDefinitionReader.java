package reader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/***
 *
 *@Author ChenjunWang
 *@Description:
 *@Date: Created in 18:23 2020/3/1
 *@Modified By:
 *
 */
public class BeanDefinitionReader {

    private Properties config = new Properties();

    private List<String> registryBeanClasses = new ArrayList<String>();

    private final String SCAN_PACKAGE = "basePackage";


    public BeanDefinitionReader(String... locations) {

        Arrays.stream(locations).forEach(location -> {
            InputStream is = ClassLoader.getSystemResourceAsStream(location);
            try {
                config.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String path = config.getProperty(SCAN_PACKAGE);
            scan(path);

        });


    }

    private void scan(String path) {

        URL url = ClassLoader.getSystemResource(path.replaceAll("\\.", "/"));

        //获得要扫描类的文件目录
        File[] files = new File(url.getFile()).listFiles();
        //在目录下找到需要给框架容器托管的bean，并加入到容器
        for (File file : files) {

            if (file.isDirectory()) {
                scan(file.getPath());

            } else {
                if (file.getName().endsWith(".class")) {
                    registryBeanClasses.add(path + "." + file.getName().replace(".class", ""));
                }

            }
        }

    }

    public Properties getConfig() {
        return config;
    }


    public List<String> getRegistryBeanClasses() {
        return registryBeanClasses;
    }

}

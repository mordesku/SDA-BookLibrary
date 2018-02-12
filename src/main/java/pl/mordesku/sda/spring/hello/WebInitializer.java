package pl.mordesku.sda.spring.hello;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;


public class WebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    public static final int MAX_FILE_SIZE = 1024 * 1024 * 10;

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { AppJavaConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        File uploadDirectory = new File(AppProperties.FILE_STORE_PATH);

        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        MAX_FILE_SIZE, MAX_FILE_SIZE * 2, MAX_FILE_SIZE / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }
}
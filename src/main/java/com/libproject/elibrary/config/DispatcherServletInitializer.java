package com.libproject.elibrary.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
     protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebApplicationContextConfig.class };
    }

     @Override
     protected Class<?>[] getRootConfigClasses() {
    	 return null;
    }

     @Override
     protected String[] getServletMappings() {
    	   return new String[] { "/"};
     }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {

        // temp file will be uploaded here
        File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));

        // register a MultipartConfigElement
        int maxUploadSizeInMb = 100 * 1024 * 1024;
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);

    }

    @Override
    protected Filter[] getServletFilters() {
        Filter [] singleton = { new CORSFilter() };
        return singleton;
    }
}

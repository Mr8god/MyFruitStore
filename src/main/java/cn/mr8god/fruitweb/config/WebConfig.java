package cn.mr8god.fruitweb.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * @author Mr8god
 * @date 2020年11月26日04:07:34
 * @time 2020年11月26日04:07:36
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cn.mr8god.fruitweb")
public class WebConfig implements WebMvcConfigurer {
    @Bean
    @Qualifier("viewResolver")
    public ViewResolver viewResolver() {
        UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewClass(JstlView.class);
        urlBasedViewResolver.setPrefix("/WEB-INF/jsp/");
        urlBasedViewResolver.setSuffix(".jsp");
        return urlBasedViewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/static/");
    }
}

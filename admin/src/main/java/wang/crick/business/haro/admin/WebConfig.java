package wang.crick.business.haro.admin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import wang.crick.business.haro.admin.interceptor.LoginInterceptor;
import wang.crick.business.haro.core.base.helper.session.DefaultHttpSessionManager;
import wang.crick.business.haro.core.base.helper.session.HelperHttpSession;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public HelperHttpSession helperHttpSession(){
        HelperHttpSession helperHttpSession = new HelperHttpSession();
        helperHttpSession.setHttpSessionManager(new DefaultHttpSessionManager());
        return helperHttpSession;
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .excludePathPatterns("/login/**", "/static/**")
                .addPathPatterns("/**");
    }
}

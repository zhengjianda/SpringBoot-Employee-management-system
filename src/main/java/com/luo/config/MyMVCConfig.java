package com.luo.config;

        import com.luo.controller.LoginHandlerInterceptor;
        import org.springframework.beans.factory.annotation.Configurable;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.LocaleResolver;
        import org.springframework.web.servlet.View;
        import org.springframework.web.servlet.ViewResolver;
        import org.springframework.web.servlet.config.annotation.EnableWebMvc;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

        import java.util.Locale;

//如果 你想div一些定制化的功能，只要写这个组件，然后将它交给SpringBoot SpringBoot就会帮我们自动装配
//扩展springmvc dispatchservelt
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {

        public void addViewControllers(ViewControllerRegistry registry){
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("dashboard");
        }

        // 添加拦截器
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/css/**","/js/**","/img/**");
        }

        //自定义的国际化组件生效
        @Bean
        public LocaleResolver localeResolver(){
                return new MyLocaleResolver();
        }

}

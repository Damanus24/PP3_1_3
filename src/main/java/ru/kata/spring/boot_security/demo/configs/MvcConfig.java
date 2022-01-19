package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /*Этот метод используется в Spring Framework для добавления контроллера, который отображает вид на
    определенный URL-адрес веб-приложения. В данном случае, метод addViewController() добавляет контроллер
    для пути /user и устанавливает имя представления для этого контроллера как "user". Когда пользователь
    попадает на страницу /user, будет отображаться представление с именем "user", которое может быть
    настроено в конфигурации представлений веб-приложения.*/

//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/user").setViewName("user");
//    }
}

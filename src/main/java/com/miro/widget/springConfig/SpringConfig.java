package com.miro.widget.springConfig;

import com.miro.widget.domain.Widget;
import com.miro.widget.repository.InMemoryWidgetRepository;
import com.miro.widget.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public Repository<Widget> repository() {
        return new InMemoryWidgetRepository();
    }


}

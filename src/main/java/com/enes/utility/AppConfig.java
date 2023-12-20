package com.enes.utility;

import com.enes.repository.entity.Category;
import com.enes.repository.entity.Post;
import com.enes.repository.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("category")
    @Primary
    public Class<Category> getCategoryClass(){
        return Category.class;
    }

    @Bean
    @Qualifier("post")
    public Class<Post> getPostClass(){
        return Post.class;
    }

    @Bean
    @Qualifier("user")
    public Class<User> getUserClass(){
        return User.class;
    }
}

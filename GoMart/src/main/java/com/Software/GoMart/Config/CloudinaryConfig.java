package com.Software.GoMart.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dd2ifqakn",
            "api_key", "759965584518387",
            "api_secret", "5TdQ0tL29PZPFVMXqGApYt67mEc"
        ));
    }
}
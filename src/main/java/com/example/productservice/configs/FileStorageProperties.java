package com.example.productservice.configs;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadLogoDir;

    private String uploadProductImageDir;

    private String uploadUserImageDir;

    private String uploadThumbnailImageDir;

}

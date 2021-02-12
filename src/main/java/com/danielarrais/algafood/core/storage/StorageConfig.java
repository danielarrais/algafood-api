package com.danielarrais.algafood.core.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.danielarrais.algafood.domain.service.FotoStorageService;
import com.danielarrais.algafood.infraestructure.service.storage.LocalFotoStorageServiceImpl;
import com.danielarrais.algafood.infraestructure.service.storage.S3FotoStorageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.danielarrais.algafood.core.storage.StorageProperties.TipoStorage.S3;

@Configuration
public class StorageConfig {
    private final StorageProperties storageProperties;

    public StorageConfig(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Bean
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
            storageProperties.getS3().getIdChaveAcesso(),
            storageProperties.getS3().getChaveAcesso()
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegiao())
                .build();
    }

    @Bean
    public FotoStorageService fotoStorageService() {
        if (storageProperties.getTipoStorage().equals(S3)) {
            return new S3FotoStorageServiceImpl(amazonS3(), storageProperties);
        } else {
            return new LocalFotoStorageServiceImpl(storageProperties);
        }
    }
}

package com.danielarrais.algafood.infraestructure.service.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.danielarrais.algafood.core.storage.StorageProperties;
import com.danielarrais.algafood.domain.service.FotoStorageService;
import com.danielarrais.algafood.infraestructure.exceptions.StorageException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class S3FotoStorageServiceImpl implements FotoStorageService {

    private final AmazonS3 amazonS3;
    private final StorageProperties storageProperties;

    public S3FotoStorageServiceImpl(AmazonS3 amazonS3, StorageProperties storageProperties) {
        this.amazonS3 = amazonS3;
        this.storageProperties = storageProperties;
    }

    @Override
    public void storage(Foto foto) {
        var caminhoArquivo = String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), foto.getNomeArquivo());

        var objectMetadata = new ObjectMetadata() {{
            setContentType(foto.getContentType());
        }};

        try {
            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo,
                    foto.getInputStream(),
                    objectMetadata
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar o arquivo para Amazon S3", e);
        }
    }

    @Override
    public void delete(String fileName) {
        var caminhoArquivo = String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), fileName);

        try {
            var putObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(),
                    caminhoArquivo
            );

            amazonS3.deleteObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível remover o arquivo do Amazon S3", e);
        }
    }

    @Override
    public FileInputStream recover(String fileName) {
        return null;
    }
}

package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.response.ContentDownloadingFailedException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import static com.hellotranslate.connector.exception.response.LocHubErrorCodes.CONNECTOR_OPERATION_FAILED;

@Service
public class ContentConverterService {

    public String inputStreamToBase64String(InputStream binaryContent) throws ContentDownloadingFailedException, IOException {
        if (binaryContent == null) { //todo describe in docs
            throw new ContentDownloadingFailedException("Content downloading failed", CONNECTOR_OPERATION_FAILED);
        }
        return Base64.getEncoder().encodeToString(binaryContent.readAllBytes());
    }

    public InputStream stringToInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes());
    }
}

package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ContentConversionException;
import com.hellotranslate.connector.exception.jsonrpc.response.ContentDownloadingFailedException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class ContentConversionService {

    /**
     * The {@link ContentDownloadingFailedException} is thrown when input stream is null.
     * If the file had no content, do not pass null to this method.
     * Pass an empty InputStream instead.
     */
    public String inputStreamToBase64String(InputStream binaryContent) {
        if (binaryContent == null) {
            throw new ContentDownloadingFailedException("Content downloading failed");
        }
        try {
            return Base64.getEncoder().encodeToString(binaryContent.readAllBytes());
        } catch (IOException e) {
            throw new ContentConversionException("Failed to convert content to base64 format");
        }
    }

    public InputStream stringToInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes());
    }
}

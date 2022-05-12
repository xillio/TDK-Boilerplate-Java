package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.jsonrpc.response.ContentDownloadingFailedException;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import static com.hellotranslate.connector.exception.lochub.LocHubErrors.CONNECTOR_OPERATION_FAILED;

@Service
public class ContentConverterService {

    /**
     * @throws ContentDownloadingFailedException
     * The {@link ContentDownloadingFailedException} when input stream is null.
     * If the file had no content, do not pass null to this method.
     * Pass an empty InputStream instead.
     */
    public String inputStreamToBase64String(String requestId, InputStream binaryContent) throws ContentDownloadingFailedException, IOException {
        if (binaryContent == null) {
            throw new ContentDownloadingFailedException(requestId, "Content downloading failed", CONNECTOR_OPERATION_FAILED.code());
        }
        return Base64.getEncoder().encodeToString(binaryContent.readAllBytes());
    }

    public InputStream stringToInputStream(String string) {
        return new ByteArrayInputStream(string.getBytes());
    }
}

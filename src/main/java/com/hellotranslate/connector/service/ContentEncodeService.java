package com.hellotranslate.connector.service;

import com.hellotranslate.connector.exception.response.NoContentDownloaded;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import static com.hellotranslate.connector.jsonrpc.response.LocHubErrorCodes.NO_BINARY_CONTENT;

@Service
public class ContentEncodeService {

    public String convertToBase64String(InputStream binaryContent) throws NoContentDownloaded, IOException {
        if (binaryContent == null) {
            throw new NoContentDownloaded("No content downloaded", NO_BINARY_CONTENT);
        }

        var stringContent = Arrays.toString(binaryContent.readAllBytes());
        return new String(DatatypeConverter.parseBase64Binary(stringContent));
    }
}

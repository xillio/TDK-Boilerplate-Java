package com.hellotranslate.connector.service;

import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

@Service
public class ContentEncodeService {


    public String convertToBase64String(InputStream binaryContent)
            throws IOException
    {
        if (binaryContent == null) {
            throw new IOException(); // todo create specific exception with NO_BINARY_CONTENT message
        }

        var stringContent = Arrays.toString(binaryContent.readAllBytes());
        return new String(DatatypeConverter.parseBase64Binary(stringContent));
    }
}

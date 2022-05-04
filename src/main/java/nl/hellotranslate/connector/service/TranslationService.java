package nl.hellotranslate.connector.service;

import lombok.RequiredArgsConstructor;
import nl.hellotranslate.connector.jsonrpc.request.RequestDto;
import nl.hellotranslate.connector.jsonrpc.response.ResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationService {

    public ResponseDto upload(RequestDto requestDto) //todo change to input stream
    {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}

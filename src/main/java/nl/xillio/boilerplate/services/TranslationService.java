package nl.xillio.boilerplate.services;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslationService {

    public BoilerplateResponseDto upload(BoilerplateRequestDto requestDto)
    {
        try {
            return null;
        } catch (Exception e) {

            return null;
        }
    }
}

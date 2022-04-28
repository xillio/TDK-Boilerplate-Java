package nl.xillio.boilerplate.controllers;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.services.RequestBodyValidationService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class BoilerplateController {

    private final RequestBodyValidationService validator;

    @PostMapping("/deliver-content-metadata/{contentId}")
    public BoilerplateRequestDto deliverContentMetadata(
            @PathVariable("contentId") UUID contentId,
            @RequestBody BoilerplateRequestDto requestDto)
    {
        if (validator.isNotValid(requestDto)) {
            return null;
        }

        return null;
    }

    @GetMapping("/download-content/{contentId}")
    public BoilerplateRequestDto downloadBinaryContent(@PathVariable("contentId") UUID contentId)
    {
        return null;
    }

    @PostMapping("/upload-content/{contentId}")
    public BoilerplateRequestDto uploadBinaryContent(
            @PathVariable("contentId") UUID contentId,
            @RequestBody BoilerplateRequestDto requestDto)
    {
        return null;
    }

    @PostMapping("upload-translation/{translationId}")
    public BoilerplateRequestDto uploadTranslation(
            @PathVariable("translationId") UUID translationId,
            @RequestBody BoilerplateRequestDto requestDto)
    {
        return null;
    }
}

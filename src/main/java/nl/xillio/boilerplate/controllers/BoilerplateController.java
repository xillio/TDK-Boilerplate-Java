package nl.xillio.boilerplate.controllers;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDtoFactory;
import nl.xillio.boilerplate.services.ContentService;
import nl.xillio.boilerplate.services.MetadataService;
import nl.xillio.boilerplate.services.RequestBodyValidationService;
import nl.xillio.boilerplate.services.TranslationService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = {})
@RequiredArgsConstructor
public class BoilerplateController {

    private final RequestBodyValidationService validator;
    private final BoilerplateResponseDtoFactory responseFactory;

    private final MetadataService metadataService;
    private final ContentService contentService;
    private final TranslationService translationService;

    @PostMapping("/deliver-content-metadata")
    public BoilerplateResponseDto deliverContentMetadata(
            @RequestBody BoilerplateRequestDto requestDto)
    {
        return validator.isNotValid(requestDto)
               ? responseFactory.invalidConfigurationResponse(requestDto.id())
               : metadataService.deliver(requestDto);
    }

    @GetMapping("/download-content/{contentId}")
    public BoilerplateResponseDto downloadBinaryContent(@PathVariable("contentId") UUID contentId)
    {
        return contentService.download(contentId);
    }

    @PostMapping("/upload-content")
    public BoilerplateResponseDto uploadBinaryContent(@RequestBody BoilerplateRequestDto requestDto)
    {
        return validator.isNotValid(requestDto)
               ? responseFactory.invalidConfigurationResponse(requestDto.id())
               : contentService.upload(requestDto);
    }

    @PostMapping("/upload-translation")
    public BoilerplateResponseDto uploadTranslation(@RequestBody BoilerplateRequestDto requestDto)
    {
        return validator.isNotValid(requestDto)
               ? responseFactory.invalidConfigurationResponse(requestDto.id())
               : translationService.upload(requestDto);
    }
}

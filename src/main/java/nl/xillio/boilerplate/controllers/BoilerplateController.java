package nl.xillio.boilerplate.controllers;

import lombok.RequiredArgsConstructor;
import nl.xillio.boilerplate.http.request.BoilerplateRequestDto;
import nl.xillio.boilerplate.http.response.BoilerplateResponseDto;
import nl.xillio.boilerplate.services.ContentService;
import nl.xillio.boilerplate.services.MetadataService;
import nl.xillio.boilerplate.services.TranslationService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = {})
@RequiredArgsConstructor
public class BoilerplateController {

    private final MetadataService metadataService;
    private final ContentService contentService;
    private final TranslationService translationService;

    @PostMapping("/deliver-content-metadata/{contentId}")
    public BoilerplateResponseDto deliverContentMetadata(
            @PathVariable("contentId") UUID contentId,
            @RequestBody BoilerplateRequestDto requestDto)
    {
        return metadataService.deliver(contentId, requestDto);
    }

    @GetMapping("/download-content/{contentId}")
    public BoilerplateResponseDto downloadBinaryContent(@PathVariable("contentId") UUID contentId)
    {
        return contentService.download(contentId);
    }

    @PostMapping("/upload-content/{contentId}")
    public BoilerplateResponseDto uploadBinaryContent(
            @PathVariable("contentId") UUID contentId,
            @RequestBody BoilerplateRequestDto requestDto)
    {
        return contentService.upload(contentId, requestDto);
    }

    @PostMapping("upload-translation/{translationId}")
    public BoilerplateResponseDto uploadTranslation(
            @PathVariable("translationId") UUID translationId,
            @RequestBody BoilerplateRequestDto requestDto)
    {
        return translationService.upload(translationId, requestDto);
    }
}

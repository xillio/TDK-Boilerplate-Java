package nl.xillio.boilerplate.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class BoilerplateController {

    @PostMapping("/deliver-content-metadata/{contentId}")
    public Map<String, Object> deliverContentMetadata(
            @PathVariable("contentId") UUID contentId,
            @RequestBody Map<String, Object> metadata)
    {
        return null;
    }

    @GetMapping("/download-content/{contentId}")
    public Map<String, Object> downloadBinaryContent(@PathVariable("contentId") UUID contentId)
    {
        return null;
    }

    @PostMapping("/upload-content/{contentId}")
    public Map<String, Object> uploadBinaryContent(
            @PathVariable("contentId") UUID contentId,
            @RequestBody Map<String, Object> metadata)
    {
        return null;
    }

    @PostMapping("upload-translation/{translationId}")
    public Map<String, Object> uploadTranslation(
            @PathVariable("translationId") UUID translationId,
            @RequestBody Map<String, Object> metadata)
    {
        return null;
    }
}

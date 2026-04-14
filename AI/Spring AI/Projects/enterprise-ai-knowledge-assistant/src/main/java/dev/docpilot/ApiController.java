/*
@author CodeChamps0419
 */
package dev.docpilot;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final KnowledgeService knowledge;

    ApiController(KnowledgeService knowledge) {
        this.knowledge = knowledge;
    }

    @GetMapping("/me")
    Object me(Authentication authentication) {
        return Map.of(
                "username", authentication.getName(),
                "roles", authentication.getAuthorities().stream().map(Object::toString).toList());
    }

    @GetMapping("/documents")
    Object documents(Authentication authentication) {
        return knowledge.list(authentication);
    }

    @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Object upload(
            @RequestPart("file") MultipartFile file,
            @RequestParam(defaultValue = "PRIVATE") String visibility,
            @RequestParam(required = false) String team,
            Authentication authentication) throws Exception {
        return knowledge.upload(file, visibility, team, authentication);
    }

    @DeleteMapping("/documents/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id, Authentication authentication) {
        return knowledge.delete(id, authentication)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    record AskRequest(@NotBlank String question) {
    }

    @PostMapping("/ask")
    Object ask(@Valid @RequestBody AskRequest request, Authentication authentication) {
        return knowledge.answer(request.question(), authentication);
    }

    @RestControllerAdvice
    static class Errors {

        @ExceptionHandler(IllegalArgumentException.class)
        ResponseEntity<?> invalid(IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body(Map.of("error", exception.getMessage()));
        }

        @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
        ResponseEntity<?> forbidden(Exception exception) {
            return ResponseEntity.status(403).body(Map.of("error", "Access denied"));
        }
    }
}

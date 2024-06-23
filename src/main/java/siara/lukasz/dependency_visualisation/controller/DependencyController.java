package siara.lukasz.dependency_visualisation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import siara.lukasz.dependency_visualisation.model.Dependency;
import siara.lukasz.dependency_visualisation.service.DependencyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/dependencies")
public class DependencyController {

    private final DependencyService dependencyService;

    @GetMapping()
    public List<Dependency> getDependencies() {
        return dependencyService.getDependencies();
    }

    @GetMapping("/csv")
    public ResponseEntity<Object> getDependenciesCsv() {
        return ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dependencies.csv")
             .header(HttpHeaders.CONTENT_TYPE, "text/csv")
             .body(dependencyService.getDependenciesCsv());
    }

    @GetMapping("/pdf")
    public ResponseEntity<Object> getDependenciesPdf() {
        return ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dependencies.pdf")
             .header(HttpHeaders.CONTENT_TYPE, "text/pdf")
             .body(dependencyService.getDependenciesPdf());
    }

}

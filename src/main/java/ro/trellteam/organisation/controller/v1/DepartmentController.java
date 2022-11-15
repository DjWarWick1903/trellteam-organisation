package ro.trellteam.organisation.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.trellteam.organisation.dto.request.department.CreateDepartmentRequest;
import ro.trellteam.organisation.dto.request.department.DeleteDepartmentRequest;
import ro.trellteam.organisation.dto.request.department.UpdateDepartmentRequest;
import ro.trellteam.organisation.dto.response.ObjectResponse;
import ro.trellteam.organisation.exceptions.TrellGenericException;
import ro.trellteam.organisation.service.v1.DepartmentService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/department/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/main/{id}")
    public ResponseEntity<ObjectResponse> getDepartmentById(@PathVariable Long id) {
        log.debug("DepartmentController--getDepartmentById--IN");
        if(id == null) {
            throw new TrellGenericException("ORG_ERR_4");
        }
        return ResponseEntity.ok().body(departmentService.getDepartmentById(id));
    }

    @GetMapping("/organisation/{idOrg}")
    public ResponseEntity<ObjectResponse> getOrganisationDepartments(@PathVariable Long idOrg) {
        log.debug("DepartmentController--getOrganisationDepartments--IN");
        if(idOrg == null) {
            throw new TrellGenericException("ORG_ERR_4");
        }
        return ResponseEntity.ok().body(departmentService.getOrganisationDepartments(idOrg));
    }

    @PostMapping("/main")
    public ResponseEntity<ObjectResponse> createDepartment(@RequestBody @Valid CreateDepartmentRequest payload) {
        log.debug("DepartmentController--createDepartment--IN");
        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/department/v1/main").toUriString());
        log.debug("DepartmentController--createDepartment--uri: {}", uri);

        return ResponseEntity.created(uri).body(departmentService.createDepartment(payload));
    }

    @DeleteMapping("/main")
    public ResponseEntity<ObjectResponse> deleteDepartment(@RequestBody @Valid DeleteDepartmentRequest payload) {
        log.debug("DepartmentController--deleteDepartment--IN");
        return ResponseEntity.ok().body(departmentService.deleteDepartment(payload));
    }

    @PutMapping("/main")
    public ResponseEntity<ObjectResponse> updateDepartment(@RequestBody @Valid UpdateDepartmentRequest payload) {
        log.debug("DepartmentController--updateDepartment--IN");
        return ResponseEntity.ok().body(departmentService.updateDepartment(payload));
    }
}

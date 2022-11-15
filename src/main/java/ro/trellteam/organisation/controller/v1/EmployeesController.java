package ro.trellteam.organisation.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ro.trellteam.organisation.dto.request.employee.AssignEmployeeRequest;
import ro.trellteam.organisation.dto.request.employee.CreateEmployeeRequest;
import ro.trellteam.organisation.dto.response.ObjectResponse;
import ro.trellteam.organisation.exceptions.TrellGenericException;
import ro.trellteam.organisation.service.v1.EmployeeService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employee/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class EmployeesController {
    private final EmployeeService employeeService;

    @GetMapping("/organisation/{idOrg}")
    public ResponseEntity<ObjectResponse> getOrganisationEmployees(@PathVariable Long idOrg) {
        log.debug("EmployeesController--getOrganisationEmployees--IN");
        if(idOrg == null) {
            throw new TrellGenericException("ORG_ERR_4");
        }
        return ResponseEntity.ok().body(employeeService.listOrganisationEmployees(idOrg));
    }

    @PostMapping("/main")
    public ResponseEntity<ObjectResponse> createEmployee(@RequestBody @Valid CreateEmployeeRequest payload) {
        log.debug("EmployeesController--createEmployee--IN");
        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/employee/v1/main").toUriString());

        return ResponseEntity.created(uri).body(employeeService.createEmployee(payload));
    }

    @PostMapping("/department/assign")
    public ResponseEntity<ObjectResponse> assignEmployeeToDepartment(@RequestBody @Valid AssignEmployeeRequest payload) {
        log.debug("EmployeesController--assignEmployeeToDepartment--IN");
        return ResponseEntity.ok().body(employeeService.assignEmployeeToDepartment(payload));
    }

    @PostMapping("/department/unassign")
    public ResponseEntity<?> unassignEmployeeFromDepartment(@RequestBody @Valid AssignEmployeeRequest payload) {
        log.debug("EmployeesController--unassignEmployeeToDepartment--IN");
        return ResponseEntity.ok().body(employeeService.unassignEmployeeFromDepartment(payload));
    }
}

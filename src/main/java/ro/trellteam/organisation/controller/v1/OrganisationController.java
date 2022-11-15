package ro.trellteam.organisation.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.trellteam.organisation.dto.request.organisation.RegisterOrganisationRequest;
import ro.trellteam.organisation.dto.response.ObjectResponse;
import ro.trellteam.organisation.exceptions.TrellGenericException;
import ro.trellteam.organisation.service.v1.OrganisationService;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/organisation/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class OrganisationController {
    private final OrganisationService organisationService;

    @GetMapping("/main/{userID}")
    public ResponseEntity<ObjectResponse> getUserOrganisation(@PathVariable Long userID) {
        log.debug("OrganisationController--getUserOrganisation--IN");
        if(userID == null) {
            throw new TrellGenericException("ORG_ERR_4");
        }
        return ResponseEntity.ok().body(organisationService.getUserOrganisation(userID));
    }

    @PostMapping("/main")
    public ResponseEntity<ObjectResponse> registerOrganisation(@RequestBody @Valid RegisterOrganisationRequest payload) {
        log.debug("OrganisationController--registerOrganisation--IN");
        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/organisation/v1/main").toUriString());
        return ResponseEntity.created(uri).body(organisationService.registerOrganisation(payload));
    }
}

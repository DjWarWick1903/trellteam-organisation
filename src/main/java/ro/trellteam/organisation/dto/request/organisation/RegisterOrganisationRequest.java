package ro.trellteam.organisation.dto.request.organisation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.trellteam.organisation.dto.OrganisationDto;
import ro.trellteam.organisation.dto.request.external.AccountDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class RegisterOrganisationRequest {
    @Valid
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("organisation")
    private OrganisationDto organisationDto;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("department")
    private String departmentName;
    @Valid
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("account")
    private AccountDto accountDto;
}

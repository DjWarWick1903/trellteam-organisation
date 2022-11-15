package ro.trellteam.organisation.dto.request.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateDepartmentRequest {
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("name")
    private String name;

    @JsonProperty("idManager")
    private Long idManager;

    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("idOrganisation")
    private Long idOrganisation;
}

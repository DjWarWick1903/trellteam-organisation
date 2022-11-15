package ro.trellteam.organisation.dto.request.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class DeleteDepartmentRequest {
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("idOrganisation")
    private Long idOrganisation;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("name")
    private String name;
}

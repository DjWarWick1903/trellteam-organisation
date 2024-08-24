package ro.trellteam.organisation.data.dto.request.department;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

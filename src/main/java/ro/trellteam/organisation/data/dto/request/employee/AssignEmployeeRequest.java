package ro.trellteam.organisation.data.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AssignEmployeeRequest {
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("idOrganisation")
    private Long idOrganisation;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("idEmployee")
    private Long idEmployee;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("departmentName")
    private String departmentName;
}

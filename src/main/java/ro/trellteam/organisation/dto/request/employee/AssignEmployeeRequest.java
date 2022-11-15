package ro.trellteam.organisation.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

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

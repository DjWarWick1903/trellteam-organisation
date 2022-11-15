package ro.trellteam.organisation.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.trellteam.organisation.dto.EmployeeDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class CreateEmployeeRequest {
    @Valid
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("employee")
    private EmployeeDto employee;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("idDepartment")
    private Long idDepartment;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("idRole")
    private Long idRole;
}

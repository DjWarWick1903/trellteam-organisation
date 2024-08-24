package ro.trellteam.organisation.data.dto.request.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.trellteam.organisation.data.dto.EmployeeDto;

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

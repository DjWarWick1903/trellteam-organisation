package ro.trellteam.organisation.data.dto.request.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.trellteam.organisation.data.dto.EmployeeDto;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    @JsonProperty("id")
    private Long id;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("username")
    private String username;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("email")
    private String email;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("password")
    private String password;
    @JsonProperty("dateCreated")
    private Date dateCreated;
    @JsonProperty("disabled")
    private Integer disabled;
    @Valid
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("employee")
    private EmployeeDto employee;
    @JsonProperty("roles")
    private List<RoleDto> roles;
}

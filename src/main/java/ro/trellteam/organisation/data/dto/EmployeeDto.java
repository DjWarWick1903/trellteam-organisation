package ro.trellteam.organisation.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    @JsonProperty("id")
    private Long id;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("firstName")
    private String firstName;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phone")
    private String phone;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("cnp")
    private String CNP;
    @NotNull(message = "ORG_ERR_5")
    @JsonProperty("birthday")
    private Date bday;
}

package ro.trellteam.organisation.data.dto.request.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("name")
    private String name;
}

package ro.trellteam.organisation.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.organisation.repository.domain.Employee;
import ro.trellteam.organisation.data.dto.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToDomain(EmployeeDto employeeDto);
    EmployeeDto domainToDto(Employee employee);
}

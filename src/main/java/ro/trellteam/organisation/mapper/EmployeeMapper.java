package ro.trellteam.organisation.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.organisation.domain.Employee;
import ro.trellteam.organisation.dto.EmployeeDto;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee dtoToDomain(EmployeeDto employeeDto);
    EmployeeDto domainToDto(Employee employee);
}

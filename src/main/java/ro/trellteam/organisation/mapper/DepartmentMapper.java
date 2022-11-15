package ro.trellteam.organisation.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.organisation.domain.Department;
import ro.trellteam.organisation.dto.DepartmentDto;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department dtoToDomain(DepartmentDto departmentDto);
    DepartmentDto domainToDto(Department department);
}

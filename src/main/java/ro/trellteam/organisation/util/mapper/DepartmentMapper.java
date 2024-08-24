package ro.trellteam.organisation.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.organisation.repository.domain.Department;
import ro.trellteam.organisation.data.dto.DepartmentDto;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    Department dtoToDomain(DepartmentDto departmentDto);
    DepartmentDto domainToDto(Department department);
}

package ro.trellteam.organisation.service.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.trellteam.organisation.domain.Department;
import ro.trellteam.organisation.domain.Employee;
import ro.trellteam.organisation.domain.Organisation;
import ro.trellteam.organisation.dto.request.external.AccountDto;
import ro.trellteam.organisation.dto.request.external.RoleDto;
import ro.trellteam.organisation.dto.request.organisation.RegisterOrganisationRequest;
import ro.trellteam.organisation.dto.response.ObjectResponse;
import ro.trellteam.organisation.exceptions.TrellGenericException;
import ro.trellteam.organisation.mapper.EmployeeMapper;
import ro.trellteam.organisation.mapper.OrganisationMapper;
import ro.trellteam.organisation.repository.DepartmentRepositoryImpl;
import ro.trellteam.organisation.repository.EmployeeRepositoryImpl;
import ro.trellteam.organisation.repository.OrganisationRepositoryImpl;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrganisationService {
    private final DepartmentRepositoryImpl departmentRepository;
    private final EmployeeRepositoryImpl employeeRepository;
    private final OrganisationRepositoryImpl organisationRepository;
    private final OrganisationMapper organisationMapper;
    private final EmployeeMapper employeeMapper;

    public ObjectResponse getUserOrganisation(final Long userID) {
        log.debug("OrganisationService--getUserOrganisation--userID: {}", userID);
        final List<Department> departments = departmentRepository.findByEmployeeId(userID);
        final Organisation organisation = organisationRepository.findByDepartmentId(departments.get(0).getId());

        return new ObjectResponse(organisationMapper.domainToDto(organisation));
    }

    @Transactional
    public ObjectResponse registerOrganisation(final RegisterOrganisationRequest request) {
        log.debug("OrganisationService--registerOrganisation--request: {}", request);
        Organisation organisation = organisationMapper.dtoToDomain(request.getOrganisationDto());
        AccountDto accountDto = request.getAccountDto();

        try {
            final Employee employee = employeeRepository.save(employeeMapper.dtoToDomain(accountDto.getEmployee()));

            //TODO: get role from security service
            RoleDto role = new RoleDto(); //set ADMIN

            accountDto.setRoles(Arrays.asList(role));
            accountDto.setEmployee(employeeMapper.domainToDto(employee));
            //TODO: encode password in security service
            //TODO: send account to be saved in security service

            Department department = new Department(null, request.getDepartmentName(), null, null);
            department.addEmployee(employee);
            organisation.addDepartment(department);
            organisation.addEmployee(employee);
            organisation = organisationRepository.save(organisation);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("ORG_ERR_12");
        }

        return new ObjectResponse(organisation);
    }
}

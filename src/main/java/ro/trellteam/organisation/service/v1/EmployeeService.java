package ro.trellteam.organisation.service.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.trellteam.organisation.domain.Department;
import ro.trellteam.organisation.domain.Employee;
import ro.trellteam.organisation.domain.Organisation;
import ro.trellteam.organisation.dto.EmployeeDto;
import ro.trellteam.organisation.dto.request.employee.AssignEmployeeRequest;
import ro.trellteam.organisation.dto.request.employee.CreateEmployeeRequest;
import ro.trellteam.organisation.dto.response.ObjectResponse;
import ro.trellteam.organisation.exceptions.TrellGenericException;
import ro.trellteam.organisation.mapper.DepartmentMapper;
import ro.trellteam.organisation.mapper.EmployeeMapper;
import ro.trellteam.organisation.repository.DepartmentRepositoryImpl;
import ro.trellteam.organisation.repository.EmployeeRepositoryImpl;
import ro.trellteam.organisation.repository.OrganisationRepositoryImpl;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepositoryImpl employeeRepository;
    private final OrganisationRepositoryImpl organisationRepository;
    private final DepartmentRepositoryImpl departmentRepository;
    private final EmployeeMapper employeeMapper;
    private final DepartmentMapper departmentMapper;

    public ObjectResponse listOrganisationEmployees(final Long id) {
        log.debug("EmployeeService--listOrganisationEmployees--id: {}", id);
        final Set<Employee> employees = employeeRepository.listOrganisationEmployees(id);
        final Set<EmployeeDto> employeeDtos = employees.stream()
                .map(e -> employeeMapper.domainToDto(e))
                .collect(Collectors.toSet());
        final ObjectResponse response = new ObjectResponse(employeeDtos);
        return response;
    }

    public ObjectResponse unassignEmployeeFromDepartment(final AssignEmployeeRequest request) {
        log.debug("EmployeeService--unassignEmployeeToDepartment--request: {}", request);

        final Employee employee = employeeRepository.findById(request.getIdEmployee());
        final Organisation organisation = organisationRepository.findById(request.getIdOrganisation());
        final Set<Department> departments = organisation.getDepartments();

        Department department = null;
        for(final Department dep : departments) {
            if(dep.getName().equals(request.getDepartmentName())) {
                department = dep;
                break;
            }
        }

        if(department == null) {
            log.error("Requested department could not be found.");
            throw new TrellGenericException("ORG_ERR_9");
        }

        try {
            department.removeEmployee(employee);
            departmentRepository.save(department);
        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_9");
        }

        return new ObjectResponse(departmentMapper.domainToDto(department));
    }

    public ObjectResponse assignEmployeeToDepartment(final AssignEmployeeRequest request) {
        log.debug("EmployeeService--assignEmployeeToDepartment--request: {}", request);
        final Employee employee = employeeRepository.findById(request.getIdEmployee());
        final Organisation organisation = organisationRepository.findById(request.getIdOrganisation());
        final Set<Department> departments = organisation.getDepartments();

        Department department = null;
        for(final Department dep : departments) {
            if(dep.getName().equals(request.getDepartmentName())) {
                department = dep;
                break;
            }
        }

        if(department == null) {
            log.error("Requested department could not be found.");
            throw new TrellGenericException("ORG_ERR_10");
        }

        try {
            department.addEmployee(employee);
            departmentRepository.save(department);
        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_10");
        }

        return new ObjectResponse(departmentMapper.domainToDto(department));
    }

    public ObjectResponse createEmployee(final CreateEmployeeRequest request) {
        log.debug("EmployeeService--createEmployee--request: {}", request);
        Employee employee = employeeMapper.dtoToDomain(request.getEmployee());

        //TODO: add call to security service to find role
        /*final Role role = roleService.findById(request.getIdRole());
        account.addRole(role);*/

        Department department = departmentRepository.findById(request.getIdDepartment());
        Organisation organisation = organisationRepository.findByDepartmentId(department.getId());

        try {
            employee = employeeRepository.save(employee);

            department.addEmployee(employee);
            organisation.addEmployee(employee);

            departmentRepository.save(department);
            organisationRepository.save(organisation);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("ORG_ERR_11");
        }

        return new ObjectResponse(employeeMapper.domainToDto(employee));
    }
}

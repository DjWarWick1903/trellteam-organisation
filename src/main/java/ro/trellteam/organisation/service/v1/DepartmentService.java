package ro.trellteam.organisation.service.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.trellteam.organisation.domain.Department;
import ro.trellteam.organisation.domain.Employee;
import ro.trellteam.organisation.domain.Organisation;
import ro.trellteam.organisation.dto.DepartmentDto;
import ro.trellteam.organisation.dto.request.department.CreateDepartmentRequest;
import ro.trellteam.organisation.dto.request.department.DeleteDepartmentRequest;
import ro.trellteam.organisation.dto.request.department.UpdateDepartmentRequest;
import ro.trellteam.organisation.dto.response.ObjectResponse;
import ro.trellteam.organisation.exceptions.TrellGenericException;
import ro.trellteam.organisation.mapper.DepartmentMapper;
import ro.trellteam.organisation.repository.DepartmentRepositoryImpl;
import ro.trellteam.organisation.repository.EmployeeRepositoryImpl;
import ro.trellteam.organisation.repository.OrganisationRepositoryImpl;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    private final DepartmentRepositoryImpl departmentRepository;
    private final OrganisationRepositoryImpl organisationRepository;
    private final EmployeeRepositoryImpl employeeRepository;
    private final DepartmentMapper departmentMapper;

    public ObjectResponse getDepartmentById(final Long id) {
        log.debug("DepartmentService--getDepartmentById--IN");
        final Department department = departmentRepository.findById(id);
        return new ObjectResponse(departmentMapper.domainToDto(department));
    }

    public ObjectResponse getOrganisationDepartments(final Long idOrg) {
        log.debug("DepartmentService--getOrganisationDepartments--IN");
        final Organisation organisation = organisationRepository.findById(idOrg);
        final Set<Department> departments = organisation.getDepartments();
        final Set<DepartmentDto> departmentDtos = departments.stream()
                .map(e -> departmentMapper.domainToDto(e))
                .collect(Collectors.toSet());

        return new ObjectResponse(departmentDtos);
    }

    @Transactional
    public ObjectResponse createDepartment(final CreateDepartmentRequest request) {
        log.debug("DepartmentService--createDepartment--request: {}", request);

        Organisation organisation = organisationRepository.findById(request.getIdOrganisation());
        Department department = new Department();
        department.setName(request.getName());

        if(request.getIdManager() != null && request.getIdManager() != 0l) {
            final Employee manager = employeeRepository.findById(request.getIdManager());
            department.setManager(manager);
            department.addEmployee(manager);
        }

        try {
            department = departmentRepository.save(department);
            organisation.addDepartment(department);
            organisationRepository.save(organisation);
        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_6");
        }

        return new ObjectResponse(departmentMapper.domainToDto(department));
    }

    @Transactional
    public ObjectResponse deleteDepartment(final DeleteDepartmentRequest request) {
        log.debug("DepartmentService--deleteDepartment--request: {}", request);

        Organisation organisation = organisationRepository.findById(request.getIdOrganisation());
        Department department = departmentRepository.findByName(request.getName());

        try {
            organisation.removeDepartment(department);
            department.purgeEmployees();
            departmentRepository.deleteById(department.getId());
            organisationRepository.save(organisation);
        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_7");
        }

        final Set<DepartmentDto> departmentDtos = organisation.getDepartments().stream()
                .map(e -> departmentMapper.domainToDto(e))
                .collect(Collectors.toSet());

        return new ObjectResponse(departmentDtos);
    }

    public ObjectResponse updateDepartment(final UpdateDepartmentRequest request) {
        log.debug("DepartmentService--updateDepartment--request: {}", request);
        Department department = departmentRepository.findById(request.getIdDepartment());
        department.setName(request.getName());

        if(request.getIdManager() != null) {
            final Employee employee = request.getIdManager() == 0 ? null : employeeRepository.findById(request.getIdManager());
            department.setManager(employee);
        }

        try {
            department = departmentRepository.save(department);
        } catch(final Exception exception) {
            log.error(exception.getMessage());
            throw new TrellGenericException("ORG_ERR_8");
        }

        return new ObjectResponse(departmentMapper.domainToDto(department));
    }
}

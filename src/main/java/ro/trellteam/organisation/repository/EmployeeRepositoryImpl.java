package ro.trellteam.organisation.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.organisation.domain.Employee;
import ro.trellteam.organisation.domain.Organisation;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmployeeRepositoryImpl {
    private final EmployeeRepository employeeRepository;
    private final OrganisationRepositoryImpl organisationRepository;

    /**
     * Method used to return a list of employees of an organisation.
     * @param idOrg
     * @return List<Employee>
     */
    public Set<Employee> listOrganisationEmployees(final Long idOrg) {
        log.debug("EmployeeService--listOrganisationEmployees--idOrg: {}", idOrg);
        final Organisation organisation = organisationRepository.findById(idOrg);
        return organisation.getEmployees();
    }

    /**
     * Method used to return the employee starting from a provided id.
     * @param id
     * @return Employee
     */
    public Employee findById(final Long id) {
        log.debug("EmployeeService--findById--IN");
        log.debug("EmployeeService--findById--id: {}", id);
        final Employee employee = employeeRepository.findById(id).get();
        log.debug("EmployeeService--findById--employee: {}", employee);
        log.debug("EmployeeService--findById--OUT");
        return employee;
    }

    /**
     * Method used to save an employee in the database.
     * @param employee
     * @return Employee
     */
    public Employee save(Employee employee) {
        log.debug("EmployeeService--save--IN");
        employee = employeeRepository.save(employee);
        log.debug("EmployeeService--save--employee: {}", employee);
        log.debug("EmployeeService--save--IN");
        return employee;
    }

    public Employee saveAndFlush(final Employee employee) {
        log.debug("EmployeeService--saveAndFlush--IN");
        return employeeRepository.saveAndFlush(employee);
    }

    /**
     * Method used to delete an employee from the database.
     * @param id
     */
    public void deleteById(final Long id) {
        log.debug("EmployeeService--deleteById--IN");
        log.debug("EmployeeService--deleteById--id: {}", id);
        employeeRepository.deleteById(id);
        log.debug("EmployeeService--deleteById--OUT");
    }
}

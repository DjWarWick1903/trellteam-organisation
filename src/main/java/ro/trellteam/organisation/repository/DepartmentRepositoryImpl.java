package ro.trellteam.organisation.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.organisation.domain.Department;
import ro.trellteam.organisation.exceptions.TrellGenericException;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class DepartmentRepositoryImpl {
    private final DepartmentRepository departmentRepository;

    /**
     * Method used to return the department starting from a provided name.
     * @param name
     * @return Department
     */
    public Department findByName(final String name) {
        log.debug("DepartmentService--findByName--IN");
        log.debug("DepartmentService--findByName--name: {}" , name);

        Department department = null;
        try {
            department = departmentRepository.findByName(name);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_2");
        }

        log.debug("DepartmentService--findByName--department: {}" , department.toString());
        log.debug("DepartmentService--findByName--OUT");
        return department;
    }

    /**
     * Method used to find a department based on the id of an employee.
     * @param id
     * @return Department
     */
    public List<Department> findByEmployeeId(final Long id) {
        log.debug("DepartmentService--findByEmployeeId--IN");
        log.debug("DepartmentService--findByEmployeeId--id: {}" , id);

        List<Department> departments = null;
        try {
            departments = departmentRepository.findByEmployeeId(id);
        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_2");
        }

        log.debug("DepartmentService--findByEmployeeId--departments: {}" , departments);
        log.debug("DepartmentService--findByEmployeeId--OUT");
        return departments;
    }

    /**
     * Method used to return the department starting from a provided id.
     * @param id
     * @return Department
     */
    public Department findById(final Long id) {
        log.debug("DepartmentService--findById--IN");
        log.debug("DepartmentService--findById--id: {}" , id);

        Department department = null;
        try {
            department = departmentRepository.findById(id).get();
        } catch(final Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_2");
        }
        log.debug("DepartmentService--findById--department: {}" , department);
        log.debug("DepartmentService--findById--OUT");

        return department;
    }

    /**
     * Method used to save an department in the database.
     * @param department
     * @return Department
     */
    public Department save(Department department) {
        log.debug("DepartmentService--save--IN");
        department = departmentRepository.save(department);
        log.debug("DepartmentService--save--department: {}", department);
        log.debug("DepartmentService--save--OUT");
        return department;
    }

    /**
     * Method used to delete an department from the database.
     * @param id
     */
    public void deleteById(final Long id) {
        log.debug("DepartmentService--deleteById--IN");
        log.debug("DepartmentService--deleteById--id: {}" , id);
        departmentRepository.deleteById(id);
        log.debug("DepartmentService--deleteById--OUT");
    }
}

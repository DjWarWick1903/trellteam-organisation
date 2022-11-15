package ro.trellteam.organisation.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.trellteam.organisation.domain.Organisation;
import ro.trellteam.organisation.exceptions.TrellGenericException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrganisationRepositoryImpl {
    private final OrganisationRepository organisationRepository;

    /**
     * Method used to return the organisation starting from a provided name.
     * @param name
     * @return Organisation
     */
    public Organisation findByName(final String name) {
        log.debug("OrganisationService--findByName--IN");
        log.debug("OrganisationService--findByName--name: {}", name);

        Organisation organisation = null;
        try {
            organisation = organisationRepository.findByName(name);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_1");
        }

        log.debug("OrganisationService--findByName--organisation: {}", organisation);
        log.debug("OrganisationService--findByName--OUT");
        return organisation;
    }

    /**
     * Method used to retrieve an organisation based on a department's id.
     * @param id
     * @return Organisation
     */
    public Organisation findByDepartmentId(final Long id) {
        log.debug("OrganisationService--findByDepartmentId--IN");
        log.debug("OrganisationService--findByDepartmentId--id: {}", id);

        Organisation organisation = null;
        try {
            organisation = organisationRepository.findByDepartmentId(id);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_1");
        }

        log.debug("OrganisationService--findByDepartmentId--organisation: {}", organisation);
        log.debug("OrganisationService--findByDepartmentId--OUT");
        return organisation;
    }

    /**
     * Method used to return the organisation starting from a provided id.
     * @param id
     * @return Organisation
     */
    public Organisation findById(final Long id) {
        log.debug("OrganisationService--findById--IN");
        log.debug("OrganisationService--findById--id: {}", id);

        Organisation organisation = null;
        try {
            organisation = organisationRepository.findById(id).get();
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("ORG_ERR_1");
        }

        log.debug("OrganisationService--findById--organisation: {}", organisation);
        log.debug("OrganisationService--findById--OUT");

        return organisation;
    }

    /**
     * Method used to save an organisation in the database.
     * @param organisation
     * @return Organisation
     */
    public Organisation save(Organisation organisation) {
        log.debug("OrganisationService--save--IN");
        organisation = organisationRepository.save(organisation);
        log.debug("OrganisationService--save--OUT");

        return organisation;
    }

    /**
     * Method used to delete an organisation from the database.
     * @param id
     */
    public void deleteById(final Long id) {
        log.debug("OrganisationService--deleteById--IN");
        log.debug("OrganisationService--deleteById--id: {}", id);
        organisationRepository.deleteById(id);
        log.debug("OrganisationService--deleteById--OUT");
    }
}

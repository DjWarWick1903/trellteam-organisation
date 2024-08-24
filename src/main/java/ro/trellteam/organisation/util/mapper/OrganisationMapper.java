package ro.trellteam.organisation.util.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.organisation.repository.domain.Organisation;
import ro.trellteam.organisation.data.dto.OrganisationDto;

@Mapper(componentModel = "spring")
public interface OrganisationMapper {
    Organisation dtoToDomain(OrganisationDto organisationDto);
    OrganisationDto domainToDto(Organisation organisation);
}

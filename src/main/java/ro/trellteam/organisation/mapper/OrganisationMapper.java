package ro.trellteam.organisation.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.organisation.domain.Organisation;
import ro.trellteam.organisation.dto.OrganisationDto;

@Mapper(componentModel = "spring")
public interface OrganisationMapper {
    Organisation dtoToDomain(OrganisationDto organisationDto);
    OrganisationDto domainToDto(Organisation organisation);
}

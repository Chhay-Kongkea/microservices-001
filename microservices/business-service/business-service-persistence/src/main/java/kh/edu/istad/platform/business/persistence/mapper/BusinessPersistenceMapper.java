package kh.edu.istad.platform.business.persistence.mapper;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.platform.business.persistence.document.BusinessDocument;
import kh.edu.platformbusiness.domain.entity.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        imports = {BusinessId.class}
)
public interface BusinessPersistenceMapper {

    @Mapping(target = "id", expression = "java(business.getId().id())")
    @Mapping(target = "businessName", source = "businessName")
    @Mapping(target = "businessContact", source = "businessContact")
    @Mapping(target = "orderApproval", source = "orderApproval")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "orderDetail", source = "orderDetail")
    BusinessDocument toDocument(Business business);

    @Mapping(target = "id", expression = "java(new BusinessId(document.getId()))")
    @Mapping(target = "businessName", source = "businessName")
    @Mapping(target = "businessContact", source = "businessContact")
    @Mapping(target = "orderApproval", source = "orderApproval")
    @Mapping(target = "active", source = "active")
    @Mapping(target = "orderDetail", source = "orderDetail")
    Business toDomain(BusinessDocument document);
}
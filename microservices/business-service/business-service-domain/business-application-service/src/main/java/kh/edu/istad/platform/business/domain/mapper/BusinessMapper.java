package kh.edu.istad.platform.business.domain.mapper;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.platform.business.domain.dto.RegisterBusinessCommand;
import kh.edu.istad.platform.business.domain.dto.RegisterBusinessResult;
import kh.edu.platformbusiness.domain.entity.Business;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface BusinessMapper {

    default Business toBusiness(RegisterBusinessCommand command) {
        if (command == null) {
            return null;
        }

        return Business.newBuilder()
                .id(new BusinessId(UUID.randomUUID()))
                .businessName(command.businessName())
                .businessContact(command.businessContact())
                .orderApproval(command.orderApproval())
                .active(command.active())
                .orderDetail(command.orderDetail())
                .build();
    }

    default RegisterBusinessResult toRegisterBusinessResult(Business business) {
        if (business == null) {
            return null;
        }

        return new RegisterBusinessResult(
                business.getId().id().toString(),
                business.getBusinessName().displayName(),
                business.isActive()
        );
    }
}

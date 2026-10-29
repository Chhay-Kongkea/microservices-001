package kh.edu.istad.platform.business.restapi.mapper;

import kh.edu.istad.common.domain.valueobject.Money;
import kh.edu.istad.platform.business.domain.dto.RegisterBusinessCommand;
import kh.edu.istad.platform.business.domain.dto.RegisterBusinessResult;
import kh.edu.istad.platform.business.restapi.dto.BusinessRequest;
import kh.edu.istad.platform.business.restapi.dto.BusinessResponse;
import kh.edu.platformbusiness.domain.entity.OrderApproval;
import kh.edu.platformbusiness.domain.entity.OrderDetail;
import kh.edu.platformbusiness.domain.entity.Product;
import kh.edu.platformbusiness.domain.valueobject.BusinessContact;
import kh.edu.platformbusiness.domain.valueobject.BusinessName;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BusinessWebMapper {

    default RegisterBusinessCommand toCommand(BusinessRequest request) {
        if (request == null) {
            return null;
        }

        OrderDetail orderDetail = request.orderDetail() == null
                ? null
                : OrderDetail.newBuilder()
                .orderStatus(request.orderDetail().orderStatus())
                .totalAmount(new Money(request.orderDetail().totalAmount()))
                .products(request.orderDetail().products().stream()
                        .map(product -> Product.newBuilder()
                                .name(product.name())
                                .price(new Money(product.price()))
                                .quantity(product.quantity())
                                .available(product.available())
                                .build())
                        .collect(Collectors.toList()))
                .build();

        return new RegisterBusinessCommand(
                new BusinessName(
                        request.businessName().legalName(),
                        request.businessName().displayName()),
                new BusinessContact(
                        request.businessContact().email(),
                        request.businessContact().phoneNumber()),
                map(request.orderApproval()),
                request.active(),
                orderDetail
        );
    }

    BusinessResponse toResponse(RegisterBusinessResult result);

    default OrderApproval map(BusinessRequest.OrderApprovalRequest request) {
        if (request == null || request.orderApprovalStatus() == null) {
            return null;
        }

        return OrderApproval.newBuilder()
                .orderApprovalStatus(request.orderApprovalStatus())
                .build();
    }
}

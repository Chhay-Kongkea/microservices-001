package kh.edu.istad.platform.payment.restapi.mapper;


import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentResult;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentResult;
import kh.edu.istad.platform.payment.restapi.dto.InitiatePaymentRequest;
import kh.edu.istad.platform.payment.restapi.dto.InitiatePaymentResponse;
import kh.edu.istad.platform.payment.restapi.dto.UpdatePaymentRequest;
import kh.edu.istad.platform.payment.restapi.dto.UpdatePaymentResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentWebMapper {

    InitiatePaymentCommand toCommand(InitiatePaymentRequest request);
    UpdatePaymentCommand toUpdateCommand(UpdatePaymentRequest request);

    InitiatePaymentResponse toResponse(InitiatePaymentResult result);
    UpdatePaymentResponse toUpdateResponse(UpdatePaymentResult result);
}

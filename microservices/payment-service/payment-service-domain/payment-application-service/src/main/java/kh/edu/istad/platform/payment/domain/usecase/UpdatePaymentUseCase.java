package kh.edu.istad.platform.payment.domain.usecase;

import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentResult;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.mapper.PaymentMapper;
import kh.edu.istad.platform.payment.domain.port.out.CustomerValidationPort;
import kh.edu.istad.platform.payment.domain.port.out.OrderValidationPort;
import kh.edu.istad.platform.payment.domain.port.out.PaymentRepository;
import kh.edu.istad.platform.payment.domain.service.PaymentDomainService;
import kh.edu.istad.platform.payment.domain.valueobject.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePaymentUseCase {
    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderValidationPort orderValidationPort;


    public UpdatePaymentResult update(UpdatePaymentCommand command){

        orderValidationPort.existById(command.orderId());

        Payment payment  = paymentMapper.fromUpdateCommandToDomain(command);

        if(command.paymentStatus().equals(PaymentStatus.CANCELED)){
            paymentDomainService.cancelPayment(payment);
        }

        if(command.paymentStatus().equals(PaymentStatus.FAILED))
        {
            paymentDomainService.failPayment(payment);
        }

        return paymentMapper.fromDomainToUpdateResult(paymentRepository.save(payment));



    }
}

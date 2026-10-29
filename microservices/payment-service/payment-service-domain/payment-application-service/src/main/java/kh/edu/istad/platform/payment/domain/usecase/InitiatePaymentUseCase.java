package kh.edu.istad.platform.payment.domain.usecase;

import kh.edu.istad.common.domain.valueobject.CustomerId;
import kh.edu.istad.common.domain.valueobject.OrderId;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.InitiatePaymentResult;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentCommand;
import kh.edu.istad.platform.payment.domain.dto.UpdatePaymentResult;
import kh.edu.istad.platform.payment.domain.entity.Payment;
import kh.edu.istad.platform.payment.domain.mapper.PaymentMapper;
import kh.edu.istad.platform.payment.domain.port.out.CustomerValidationPort;
import kh.edu.istad.platform.payment.domain.port.out.OrderValidationPort;
import kh.edu.istad.platform.payment.domain.port.out.PaymentRepository;

import kh.edu.istad.platform.payment.domain.service.PaymentDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitiatePaymentUseCase {

    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final CustomerValidationPort customerValidationPort;
    private final OrderValidationPort orderValidationPort;


    @Transactional
    public InitiatePaymentResult execute(InitiatePaymentCommand command){

        customerValidationPort.existById(new CustomerId(command.customerId()));
        orderValidationPort.existById(new OrderId(command.orderId()));

        Payment payment = paymentMapper.fromInitiateCommandToDomain(command);
        paymentDomainService.completePayment(payment);


        return paymentMapper.fromDomainToInitiateResult(paymentRepository.save(payment));

    }



}


package kh.edu.istad.platform.payment.restapi.controller;

import kh.edu.istad.platform.payment.domain.usecase.InitiatePaymentUseCase;
import kh.edu.istad.platform.payment.domain.usecase.UpdatePaymentUseCase;
import kh.edu.istad.platform.payment.restapi.dto.InitiatePaymentRequest;
import kh.edu.istad.platform.payment.restapi.dto.InitiatePaymentResponse;
import kh.edu.istad.platform.payment.restapi.dto.UpdatePaymentRequest;
import kh.edu.istad.platform.payment.restapi.dto.UpdatePaymentResponse;
import kh.edu.istad.platform.payment.restapi.mapper.PaymentWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final InitiatePaymentUseCase initiatePaymentUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;
    private final PaymentWebMapper paymentWebMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InitiatePaymentResponse create(
            @RequestBody InitiatePaymentRequest request
    ) {
        return paymentWebMapper.toResponse(
                initiatePaymentUseCase.execute(
                        paymentWebMapper.toCommand(request)
                )
        );
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatePaymentResponse update(
            @RequestBody UpdatePaymentRequest request
    ) {
        return paymentWebMapper.toUpdateResponse(
                updatePaymentUseCase.update(
                        paymentWebMapper.toUpdateCommand(request)
                )
        );
    }
}

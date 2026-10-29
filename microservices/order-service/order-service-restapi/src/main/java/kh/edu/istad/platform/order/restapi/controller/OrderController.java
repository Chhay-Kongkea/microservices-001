package kh.edu.istad.platform.order.restapi.controller;

import jakarta.validation.Valid;
import kh.edu.istad.platform.order.domain.dto.CancelOrderResult;
import kh.edu.istad.platform.order.domain.dto.InitiateOrderResult;
import kh.edu.istad.platform.order.domain.dto.PayOrderResult;
import kh.edu.istad.platform.order.domain.usecase.CancelOrderUseCase;
import kh.edu.istad.platform.order.domain.usecase.GetOrderUseCase;
import kh.edu.istad.platform.order.domain.usecase.InitiateOrderUseCase;
import kh.edu.istad.platform.order.domain.usecase.PayOrderUseCase;

import kh.edu.istad.platform.order.restapi.dto.*;
import kh.edu.istad.platform.order.restapi.mapper.OrderWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {


    private final InitiateOrderUseCase initiateOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final PayOrderUseCase payOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final OrderWebMapper orderWebMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InitiateOrderResponse create(@Valid @RequestBody InitiateOrderRequest request){

        InitiateOrderResult result = initiateOrderUseCase.execute(orderWebMapper.toInitiateCommand(request));

        return orderWebMapper.toInitiateResponse(result);
    }


    @PostMapping("/cancel")
    @ResponseStatus(HttpStatus.OK)
    public CancelOrderResponse cancel(@Valid @RequestBody CancelOrderRequest request){

        CancelOrderResult result = cancelOrderUseCase.cancelOrder(orderWebMapper.toCancelCommand(request));

        return orderWebMapper.toCancelResponse(result);
    }


    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public PayOrderResponse pay(@Valid @RequestBody PayOrderRequest request){

        PayOrderResult result  = payOrderUseCase.payOrder(orderWebMapper.toPayOrderCommand(request));

        return orderWebMapper.toPayResponse(result);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse get(@Valid @PathVariable("id") UUID id){

          return getOrderUseCase.get(id).map(orderWebMapper::toResponse).get();
    }







}

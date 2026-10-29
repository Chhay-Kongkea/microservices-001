package kh.edu.istad.platform.business.restapi.controller;

import jakarta.validation.Valid;
import kh.edu.istad.platform.business.domain.usecase.GetBusinessUseCase;
import kh.edu.istad.platform.business.domain.usecase.RegisterBusinessUseCase;
import kh.edu.istad.platform.business.restapi.dto.BusinessRequest;
import kh.edu.istad.platform.business.restapi.dto.BusinessResponse;
import kh.edu.istad.platform.business.restapi.mapper.BusinessWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/businesses")
public class BusinessController {

    private final RegisterBusinessUseCase registerBusinessUseCase;
    private final GetBusinessUseCase getBusinessUseCase;
    private final BusinessWebMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BusinessResponse> register(@Valid @RequestBody BusinessRequest request){

          return registerBusinessUseCase.register(mapper.toCommand(request)).map(mapper::toResponse);
    }



    @GetMapping("/{businessId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<BusinessResponse> get(@Valid @PathVariable("businessId") UUID businessId){
        return getBusinessUseCase.get(businessId).map(mapper::toResponse);
    }


}

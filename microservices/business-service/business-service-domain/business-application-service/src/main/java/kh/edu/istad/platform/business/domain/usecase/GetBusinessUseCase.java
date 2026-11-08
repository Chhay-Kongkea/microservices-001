package kh.edu.istad.platform.business.domain.usecase;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.platform.business.domain.dto.RegisterBusinessResult;
import kh.edu.istad.platform.business.domain.mapper.BusinessMapper;
import kh.edu.istad.platform.business.domain.port.out.BusinessRepository;
import kh.edu.platformbusiness.domain.exception.BusinessDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetBusinessUseCase {
    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public Mono<RegisterBusinessResult> get(UUID id){

           if(id == null){
               throw  new BusinessDomainException("Business can't be null");
           }

           return businessRepository.findById(new BusinessId(id)).map(businessMapper::toRegisterBusinessResult);
    }



}

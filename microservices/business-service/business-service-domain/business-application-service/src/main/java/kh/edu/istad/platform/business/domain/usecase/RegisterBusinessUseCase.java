package kh.edu.istad.platform.business.domain.usecase;

import kh.edu.istad.platform.business.domain.dto.RegisterBusinessCommand;
import kh.edu.istad.platform.business.domain.dto.RegisterBusinessResult;
import kh.edu.istad.platform.business.domain.mapper.BusinessMapper;
import kh.edu.istad.platform.business.domain.port.out.BusinessRepository;
import kh.edu.platformbusiness.domain.entity.Business;
import kh.edu.platformbusiness.domain.exception.BusinessDomainException;
import kh.edu.platformbusiness.domain.service.BusinessDomainService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RegisterBusinessUseCase {

    private final BusinessDomainService businessDomainService;
    private final BusinessRepository businessRepository;
    private final BusinessMapper businessMapper;

    public Mono<RegisterBusinessResult> register(
            RegisterBusinessCommand command) {

        if (command == null) {
            return Mono.error(
                    new IllegalArgumentException(
                            "Register business command must not be null"
                    )
            );
        }

        return businessRepository
                .existsByLegalName(command.businessName().legalName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(
                                new BusinessDomainException(
                                        "Business legal name already exists"
                                )
                        );
                    }
                    return Mono.defer(() -> {
                        Business business =
                                businessMapper.toBusiness(command);

                        businessDomainService.registerBusiness(business);


                        return businessRepository.save(business);
                    });
                })
                .map(businessMapper::toRegisterBusinessResult);
    }
}

package kh.edu.istad.platform.business.domain.port.out;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.platformbusiness.domain.entity.Business;
import reactor.core.publisher.Mono;

public interface BusinessRepository {

    Mono<Boolean> existsByLegalName(String legalName);

    Mono<Business> findById(BusinessId businessId);

    Mono<Business> save(Business business);
}

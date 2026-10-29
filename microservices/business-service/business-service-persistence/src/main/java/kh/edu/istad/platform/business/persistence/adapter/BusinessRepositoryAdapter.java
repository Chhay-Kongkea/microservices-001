package kh.edu.istad.platform.business.persistence.adapter;

import kh.edu.istad.common.domain.valueobject.BusinessId;
import kh.edu.istad.platform.business.domain.port.out.BusinessRepository;
import kh.edu.istad.platform.business.persistence.document.BusinessDocument;
import kh.edu.istad.platform.business.persistence.mapper.BusinessPersistenceMapper;
import kh.edu.istad.platform.business.persistence.repository.BusinessMongoRepository;
import kh.edu.platformbusiness.domain.entity.Business;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

  private final BusinessMongoRepository businessMongoRepository;
  private final BusinessPersistenceMapper businessPersistenceMapper;

  @Override
  public Mono<Boolean> existsByLegalName(String legalName) {
    return businessMongoRepository.existsByBusinessName_LegalNameIgnoreCase(legalName);
  }

  @Override
  public Mono<Business> findById(BusinessId businessId) {
    return businessMongoRepository.findById(businessId.id())
            .map(businessPersistenceMapper::toDomain);
  }

  @Override
  public Mono<Business> save(Business business) {
    return Mono.just(business)
            .map(businessPersistenceMapper::toDocument)
            .flatMap(businessMongoRepository::save)
            .map(businessPersistenceMapper::toDomain);
  }
}
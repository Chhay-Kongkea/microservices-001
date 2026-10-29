package kh.edu.istad.platform.business.persistence.repository;


import kh.edu.istad.platform.business.persistence.document.BusinessDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface BusinessMongoRepository extends ReactiveMongoRepository<BusinessDocument, UUID> {
  Mono<Boolean> existsByBusinessName_LegalNameIgnoreCase(String legalName);
}

package kh.edu.istad.platform.business.persistence.document;

import kh.edu.platformbusiness.domain.entity.OrderApproval;
import kh.edu.platformbusiness.domain.entity.OrderDetail;
import kh.edu.platformbusiness.domain.valueobject.BusinessContact;
import kh.edu.platformbusiness.domain.valueobject.BusinessName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("businesses")
public class BusinessDocument {

  @Id
  private UUID id;

  private BusinessName businessName;
  private BusinessContact businessContact;
  private OrderApproval orderApproval;
  private boolean active;
  private OrderDetail orderDetail;
}
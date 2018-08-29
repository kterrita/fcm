/*
FuelConsumptionRepostitory.java
*
Avaya Inc. - Proprietary (Restricted)
Solely for authorized persons having a need to know
pursuant to Company instructions.
*
Copyright © 2008-2018 Avaya Inc. All rights reserved.
THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF Avaya Inc.
The copyright notice above does not evidence any actual
or intended publication of such source code.
*/
package ee.beleychev.fcm.repository;

import ee.beleychev.fcm.domain.FuelConsumptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author beleychev
 */
@RepositoryRestResource(collectionResourceRel = "fuelConsumptionRequests", path = "fuelConsumptionRequests")
public interface FuelConsumptionRequestRepository extends JpaRepository<FuelConsumptionRequest, Long> {

}

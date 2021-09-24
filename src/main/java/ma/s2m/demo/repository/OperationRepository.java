package ma.s2m.demo.repository;

import ma.s2m.demo.domain.Operation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long>, JpaSpecificationExecutor<Operation> {


    Operation findOperationByOperationId(String operationId);

    List<Operation> findAllByExecutionTimeStampLessThanEqual(long currentTime);

}

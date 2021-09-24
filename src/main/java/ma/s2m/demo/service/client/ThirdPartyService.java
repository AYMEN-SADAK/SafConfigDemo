package ma.s2m.demo.service.client;

import feign.FeignException;
import ma.s2m.demo.client.ISimpleClient;
import ma.s2m.demo.domain.Operation;
import ma.s2m.demo.dto.InitRequest;
import ma.s2m.demo.enums.Tranche;
import ma.s2m.demo.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThirdPartyService implements IThirdPartyService{

    private static final int FIRST_TRANCHE_MAX_TENTATIVES = 5;
    private static final int SECOND_TRANCHE_MAX_TENTATIVES = 10;
    private static final int THIRD_TRANCHE_MAX_TENTATIVES = 15;
    private static final int FIRST_TRANCHE_RETRY_TIME = 5000;
    private static final int SECOND_TRANCHE_RETRY_TIME = 60000;
    private static final int THIRD_TRANCHE_RETRY_TIME = 15 * 60000;
    private static final int FORTH_TRANCHE_RETRY_TIME = 60 * 60000;

    @Autowired
    ISimpleClient simpleClient;

    @Autowired
    OperationRepository operationRepository;

    @Override
    public void pingOnClient(InitRequest request) {
        try{
            System.out.println(simpleClient.simpleRequest());
        }catch (FeignException e){
            System.out.println(e.getCause());
            Operation operation = operationRepository.findOperationByOperationId(request.getOperationId());
            if (operation == null){
                operation = new Operation(null,request.getOperationId(),0,request.getCenter(),System.currentTimeMillis() + getRetryTime(0), getTranche(0));
            }
            operation.setTranche(getTranche(operation.getTentativeNumber()));
            operation.setTentativeNumber(operation.getTentativeNumber() + 1);
            operation.setExecutionTimeStamp(System.currentTimeMillis() + getRetryTime(operation.getTentativeNumber()));
            operationRepository.save(operation);
        }
    }

    private Tranche getTranche(int tentativeNumber){

        if (tentativeNumber < FIRST_TRANCHE_MAX_TENTATIVES){
            return Tranche.FIRST;
        }
        if (tentativeNumber < SECOND_TRANCHE_MAX_TENTATIVES){
            return Tranche.SECOND;
        }
        if (tentativeNumber < THIRD_TRANCHE_MAX_TENTATIVES){
            return Tranche.THIRD;
        }
        return Tranche.FORTH;
    }

    private long getRetryTime(int tentativeNumber){

        if (tentativeNumber < FIRST_TRANCHE_MAX_TENTATIVES){
            return FIRST_TRANCHE_RETRY_TIME;
        }
        if (tentativeNumber < SECOND_TRANCHE_MAX_TENTATIVES){
            return SECOND_TRANCHE_RETRY_TIME;
        }
        if (tentativeNumber < THIRD_TRANCHE_MAX_TENTATIVES){
            return THIRD_TRANCHE_RETRY_TIME;
        }
        return FORTH_TRANCHE_RETRY_TIME;
    }
}

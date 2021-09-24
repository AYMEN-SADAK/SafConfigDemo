package ma.s2m.demo.service.saf;

import ma.s2m.demo.domain.Operation;
import ma.s2m.demo.dto.InitRequest;
import ma.s2m.demo.enums.Tranche;
import ma.s2m.demo.repository.OperationRepository;
import ma.s2m.demo.service.client.IThirdPartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SAFService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SAFService.class);

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    IThirdPartyService thirdPartyService;

    public void fetchSafOperations(){
        operationRepository.findAllByExecutionTimeStampLessThanEqual(System.currentTimeMillis()).forEach(operation -> {
            this.retryToSendOperation(operation);
        });

    }

    private void retryToSendOperation(Operation operation){

        LOGGER.info("RETRYING TO SEND OPERATION WITH ID {}, Center : {}", operation.getOperationId(), operation.getCenter());
        InitRequest request = new InitRequest(operation.getOperationId(),operation.getCenter());
        thirdPartyService.pingOnClient(request);

    }
}

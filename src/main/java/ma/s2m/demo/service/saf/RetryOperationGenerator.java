package ma.s2m.demo.service.saf;

import ma.s2m.demo.enums.Tranche;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class RetryOperationGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryOperationGenerator.class);


    private static final int FIXED_RATE = 5000;
    private static final int DELAY_FIVE_SECONDS = 5000;

    @Autowired
    private SAFService safService;


    @Scheduled(fixedRate = FIXED_RATE, initialDelay = DELAY_FIVE_SECONDS)
    public void scheduledSAFCheck() {

        LOGGER.info("Checking on SAF Table");
        safService.fetchSafOperations();

    }


}

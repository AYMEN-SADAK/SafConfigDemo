package ma.s2m.demo.service.client;

import ma.s2m.demo.dto.InitRequest;
import org.springframework.stereotype.Service;

@Service
public interface IThirdPartyService {


    public void pingOnClient(InitRequest request);

}

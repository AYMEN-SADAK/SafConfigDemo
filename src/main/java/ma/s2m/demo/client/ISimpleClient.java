package ma.s2m.demo.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "${simple-microservice.name}", url = "${simple-microservice.api-url}")
public interface ISimpleClient {

    @GetMapping("/")
    String simpleRequest();

}
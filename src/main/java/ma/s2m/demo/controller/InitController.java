package ma.s2m.demo.controller;

import ma.s2m.demo.dto.InitRequest;
import ma.s2m.demo.service.client.IThirdPartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/init")
public class InitController {

	@Autowired
	IThirdPartyService thirdPartyService;

	@GetMapping
	public ResponseEntity<Void> init(InitRequest request) {
		System.out.println(request.toString());
		this.thirdPartyService.pingOnClient(request);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

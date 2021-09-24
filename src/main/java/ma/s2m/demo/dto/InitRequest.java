package ma.s2m.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter @AllArgsConstructor @ToString
public class InitRequest {

    private final String operationId;
    private final String center;

}

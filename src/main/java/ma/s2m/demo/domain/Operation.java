package ma.s2m.demo.domain;

import lombok.*;
import ma.s2m.demo.enums.Tranche;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "OPERATION")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERATION_SEQ")
    @SequenceGenerator(name = "OPERATION_SEQ", sequenceName = "OPERATION_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "OPERATION_ID")
    @NotBlank
    private String operationId;

    @Column(name = "TENTATIVE_NUMBER")
    private int tentativeNumber;

    @Column
    @NotBlank
    private String center;

    @Column
    private long executionTimeStamp;

    @Column
    private Tranche tranche;

}

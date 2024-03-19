package com.kuebiko.amazonemployee.dto_entity;

import jakarta.persistence.*;
import lombok.*;


@Table(schema = "amazon_company", name = "admin_login_auth")
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class AdminLoginAuthentication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "admin_ID_generator")
    //@SequenceGenerator(name ="admin_ID_generator", sequenceName = "admin_ID_sequence")

    private Long adminID;
    //Scan batchID to the scanner
    private Long batchID;

    private String firstName;
    private String lastName;
    private String department;
    private String position;

    //@NotBlank(message="Please provide email address.")
    private String email;

    @Min(value=6, message="Minimum six character.")
    @Max(value=10, message="Maximum ten character.")
    private String password;

    //@Min(value=6, message="Minimum six digit.")
    //password, pin code is hidden
    @Column(name="pin_code")
    private Integer pinCode;
}

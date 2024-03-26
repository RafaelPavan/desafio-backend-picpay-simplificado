package com.rafaelpavan.models.entities.user;

import com.rafaelpavan.models.dtos.user.UserDto;
import com.rafaelpavan.models.enums.user.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User debit(BigDecimal value){
        return new User(id, firstName, lastName, document, email, password, balance.subtract(value), userType);
    }

    public User credit(BigDecimal value){
        return new User(id, firstName, lastName, document, email, password, balance.add(value), userType);
    }


    public User(UserDto data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.document = data.document();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.password();
        this.email = data.email();
    }

}

package com.rafaelpavan.models.entities.transaction;

import com.rafaelpavan.models.entities.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name="transactions")
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public BigDecimal amount;
    @ManyToOne
    public User sender;
   @Column(name = "receiver_email")
    public String email;
    public LocalDateTime transaction_time;
}

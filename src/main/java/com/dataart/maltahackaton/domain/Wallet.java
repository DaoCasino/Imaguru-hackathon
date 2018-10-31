package com.dataart.maltahackaton.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wallet", uniqueConstraints = {@UniqueConstraint(columnNames = "address")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "address"})
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String privateKey;

    private String publicKey;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

}

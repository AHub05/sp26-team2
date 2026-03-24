package com.CSC340.MinervasList.entity;


import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Sellers")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(nullable = false)
    private String businessName;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @OneToMany(mappedBy = "Sellers", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("Sellers")
    private List<Listing> listings;

    
}
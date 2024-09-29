package com.maju_mundur.MajuMundur.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "m_role")
public class Role {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private String id;
    @Enumerated(EnumType.STRING)
    private UserRole name;

    public enum UserRole{
        MERCHANT,
        CUSTOMER,
        ADMIN

    }
}

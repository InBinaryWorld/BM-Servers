package dev.szafraniak.bmresource.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String keycloakId;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "owner")
    private List<Company> companies;
}

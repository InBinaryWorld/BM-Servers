package dev.szafraniak.bmresource.model.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Length(max = 100)
    private String keycloakId;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "owner")
    private List<Company> companies;
}

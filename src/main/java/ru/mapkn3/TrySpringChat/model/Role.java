package ru.mapkn3.TrySpringChat.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role implements PrettyEntity {
    private Long id;
    @Size(max = 20, message = "Max length for name: 20")
    @Pattern(regexp = "^[A-Za-z0-9_]+$", message = "Letters, digits and '_' only")
    private String name;
    private Collection<Account> accounts;

    public Role() {
        this(0L, "");
    }

    public Role(Long id) {
        this(id, "");
    }

    public Role(String name) {
        this(0L, name);
    }

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public Serializable primaryKey() {
        return getId();
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Role) {
            Role tmp = (Role) obj;
            return tmp.id.equals(this.id) && tmp.name.equals(this.name);
        } else {
            return false;
        }
    }
}

package ru.mapkn3.TrySpringChat.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "profile")
public class Profile implements PrettyEntity {
    private Long id;
    @Size(max = 20, message = "Max length of name: 20")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Letters only")
    private String name;
    @Size(max = 20, message = "Max length of surname: 20")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Letters only")
    private String surname;
    private Account account;

    public Profile() {
        this(0L, "", "");
    }

    public Profile(Long id) {
        this(id, "", "");
    }

    public Profile(String name, String surname) {
        this(0L, name, surname);
    }

    public Profile(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
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

    @Basic
    @Column(name = "surname", unique = true, nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public Serializable primaryKey() {
        return getId();
    }

    @Override
    public String toString() {
        return "Profile{" + "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile tmp = (Profile) obj;
            return tmp.id.equals(this.id) && tmp.name.equals(this.name) && tmp.surname.equals(this.surname);
        } else {
            return false;
        }
    }
}

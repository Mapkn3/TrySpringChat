package ru.mapkn3.TrySpringChat.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements PrettyEntity {
    private Long id;
    @Size(max = 30, message = "Max length of nickname: 30")
    @Pattern(regexp = "^[A-Za-z0-9_\\-]+$", message = "Letters, digits, '_' and '-' only")
    private String nickname;
    @Size(max = 30, message = "Max length of password: 30")
    private String password;
    private Profile profile;
    private Role role;

    public Account() {this(0L, "", "");}
    public Account(Long id) {
        this(id, "", "");
    }
    public Account(String nickname, String password) {
        this(0L, nickname, password);
    }
    public Account(Long id, String nickname, String password) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nickname", length = 30, unique = true)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "password", length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Serializable primaryKey() {
        return getId();
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", profile=" + profile.toString() +
                ", role=" + role.toString() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Account) {
            Account tmp = (Account) obj;
            return tmp.id.equals(this.id) && tmp.nickname.equals(this.nickname) && tmp.password.equals(this.password);
        } else {
            return false;
        }
    }
}

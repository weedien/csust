package cn.weedien.csust.advanced.T7_hibernate_query;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

// Group 实体类
@Entity
@Table(name = "user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_role",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> userRoles = new HashSet<>();

    public UserGroup() {
    }

    public UserGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserRole> getRoles() {
        return userRoles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setName(String name) {
        this.name = name;
    }
}


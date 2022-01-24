package entity;

import entity.enums.ERole;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private String email;
    private String bio;

    private Set<ERole> role = new HashSet<>();
    private List<Post> posts = new ArrayList<>();
    private LocalDateTime createdDate;

    @PrePersist //Изначальное назначение текущего времени до создания
    protected void OnCreate() {
        this.createdDate = LocalDateTime.now();
    }

}

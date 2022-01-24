package entity;

import javax.persistence.PrePersist;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Post {
    private Long id;
    private String title;
    private String caption;
    private String location;
    private Integer likes;

    private Set<String> likedUsers = new HashSet<>();
    private User user;
    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime createdDate;

    @PrePersist //Изначальное назначение текущего времени до создания
    protected void OnCreate() {
        this.createdDate = LocalDateTime.now();
    }
}

package com.social.media.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private SocialProfile socialProfile;

    @OneToMany(mappedBy = "socialUser")

    private List<Post> posts = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "user_group",
            joinColumns = @JoinColumn(name = "user_id"), // Table Key
            inverseJoinColumns = @JoinColumn(name = "group_id") // Table Foreign Key
    )
    private Set<SocialGroup> groups = new HashSet<>();

    // for bidirectional relationship. saving the profile with the user
    // lombok does not take care of bidirectional relationship. addition to lombok
    public void setSocialProfile(SocialProfile socialProfile) {
        socialProfile.setUser(this);
        this.socialProfile = socialProfile;
    }
}

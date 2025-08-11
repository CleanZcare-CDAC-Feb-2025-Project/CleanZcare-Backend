package com.homecleaning.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;  // changed from `title` to `text`

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_group_id")
    @JsonIgnore
    private TermGroup termGroup;
}

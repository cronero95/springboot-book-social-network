package com.springbootprojects.booknetworkapi.user;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Token {

    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private Integer id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validateAt;

    @ManyToOne
    @JoinColumn(
        name = "user_id",
        nullable = false
    )
    private User user;

}

package ru.itis.dis403.questplatform.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_quest_progress")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"user", "quest"})
@EqualsAndHashCode(exclude = {"user", "quest"})
public class UserQuestProgress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;

    @Column(nullable = false)
    private boolean completed;

    private Integer score;

    private LocalDateTime finishedAt;
}
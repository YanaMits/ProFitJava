package ProFit.web.workout;

import ProFit.web.LoginUser.LoginUser;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "workout")
public class Workout {
        @Id
        @GeneratedValue(strategy = GenerationType. IDENTITY)
        private Integer id;

        @Column(nullable = false, unique = true, length = 45)
        private Date date;

        @Column (length = 25)
        private String name;

        @ManyToOne
        @JoinColumn(name = "trainerId", referencedColumnName = "id")
        private LoginUser trainer;
}
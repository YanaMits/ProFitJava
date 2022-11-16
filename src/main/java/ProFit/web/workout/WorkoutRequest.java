package ProFit.web.workout;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkoutRequest {
    private Integer id;
    private String name;
    private Date date;
    private String trainer;
}

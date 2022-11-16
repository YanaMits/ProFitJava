package ProFit.web.workout;

import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Empty;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class WorkoutRequest {
    private final Integer id =0;
    private final String name = "";
    private final Date date = new Date();
    private final String trainer = "";
}

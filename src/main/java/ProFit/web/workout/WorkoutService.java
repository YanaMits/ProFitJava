package ProFit.web.workout;

import ProFit.web.LoginUser.LoginUser;
import ProFit.web.LoginUser.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkoutService {
    @Autowired private WorkoutRepository workoutRepository;
    @Autowired private LoginUserRepository loginUserRepository;


    public List<Workout> listAll(){
        return (List<Workout>) workoutRepository.findAll();
    }

    public void save(WorkoutRequest workoutRequest){
        Optional<LoginUser> trainer = loginUserRepository.findByUsername(workoutRequest.getTrainer());
        if(!trainer.isPresent()) {
            throw new IllegalStateException("No trainer found with name:" + workoutRequest.getTrainer());
        }

        Workout workout = workoutRequest.getId() == null
                ? new Workout()
                : workoutRepository.findById(workoutRequest.getId()).get();
        workout.setName(workoutRequest.getName());
        workout.setDate(workoutRequest.getDate());
        workout.setTrainer(trainer.get());
        workoutRepository.save(workout);
    }
    public void delete(Integer id) throws WorkoutNotFoundException {
        Optional<Workout> optionalUser = workoutRepository.findById(id);
        if(optionalUser.isPresent()){
            workoutRepository.deleteById(id);
        }
        else {
            throw new WorkoutNotFoundException("No workout found with id:" + id);
        }
    }

    public Workout getWorkoutById(Integer id) throws WorkoutNotFoundException {
        Optional<Workout> optionalUser = workoutRepository.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new WorkoutNotFoundException("No workout found with id:" + id);
    }
}

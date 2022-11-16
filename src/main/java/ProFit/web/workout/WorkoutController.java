package ProFit.web.workout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class WorkoutController {
    @Autowired
    private WorkoutService workoutService;

    @GetMapping("/workouts")
    public String showWorkoutList(Model model) {
        List<Workout> listWorkout = workoutService.listAll();
        model.addAllAttributes(Map.of("listWorkout", listWorkout));
        return "workouts";
    }

    @GetMapping("/workouts/new")
    public String showNewWorkoutForm(Model model) {
        model.addAttribute("workoutRequest", new WorkoutRequest());
        model.addAttribute("title", "Add New Workout");
        return "workout-form";
    }

    @PostMapping("/workouts/save")
    public String saveWorkout(WorkoutRequest workoutRequest, RedirectAttributes redirectAttributes) {
        workoutService.save(workoutRequest);
        redirectAttributes.addFlashAttribute("message", "Workout Saved");
        return "redirect:/workouts";
    }

    @GetMapping("/workouts/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Workout workout = workoutService.getWorkoutById(id);
            model.addAttribute("workoutRequest", new WorkoutRequest(workout.getId(),workout.getName(),workout.getDate(), workout.getTrainer().getUsername()));
            model.addAttribute("title", "Edit Workout");
            return "workout-form";
        } catch (WorkoutNotFoundException e) {
            redirectAttributes.addFlashAttribute("message", "No such workout.");
            return "redirect:/workouts";
        }
    }

    @GetMapping("/workouts/delete/{id}")
    public String deleteWorkout(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try{
            workoutService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Workout has been removed.");
        }
        catch (WorkoutNotFoundException e){
            redirectAttributes.addFlashAttribute("message", "No such workout");
        }
        return "redirect:/workouts";
    }
}



package re.edu.doctorservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @GetMapping("/{id}/schedule")
    public String checkSchedule(@PathVariable Long id) {
        return "Doctor " + id + " is available";
    }
}
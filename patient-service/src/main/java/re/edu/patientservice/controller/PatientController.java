package re.edu.patientservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    @GetMapping("/{id}")
    public String getPatient(@PathVariable Long id) {
        return "Patient " + id + " information";
    }
}
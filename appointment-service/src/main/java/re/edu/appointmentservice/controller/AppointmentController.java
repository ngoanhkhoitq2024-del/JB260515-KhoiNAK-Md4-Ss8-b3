package re.edu.appointmentservice.controller;

import lombok.RequiredArgsConstructor;
import re.edu.appointmentservice.client.DoctorClient;
import re.edu.appointmentservice.client.PatientClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final DoctorClient doctorClient;
    private final PatientClient patientClient;

    @PostMapping
    public ResponseEntity<?> createAppointment(
            @RequestParam Long doctorId,
            @RequestParam Long patientId) {

        ResponseEntity<?> patientResponse = patientClient.getPatient(patientId);

        if (!patientResponse.getStatusCode().is2xxSuccessful()) {
            return patientResponse;
        }
        return doctorClient.checkDoctorSchedule(doctorId);
    }
}
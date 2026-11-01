package re.edu.appointmentservice.client;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @Retry(name = "patientRetry", fallbackMethod = "getPatientFallback")
    public ResponseEntity<?> getPatient(Long patientId) {
        String url = "http://localhost:8081/api/v1/patients/" + patientId;
        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<?> getPatientFallback(Long patientId, Exception e) {
        return ResponseEntity
                .status(503)
                .body("Patient Service is currently unavailable. Please try again later.");
    }
}
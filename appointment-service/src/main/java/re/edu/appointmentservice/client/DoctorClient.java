package re.edu.appointmentservice.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import re.edu.appointmentservice.dto.ApiResponseError;

import java.time.LocalDateTime;

@Service
public class DoctorClient {
    private final RestTemplate restTemplate = new RestTemplate();

    @CircuitBreaker(
            name = "doctorServiceCB",
            fallbackMethod = "getDoctorFallback"
    )
    public ResponseEntity<?> checkDoctorSchedule(Long doctorId) {
        String url = "http://localhost:8082/api/v1/doctors/" + doctorId + "/schedule";

        return restTemplate.getForEntity(url, String.class);
    }

    public ResponseEntity<?> getDoctorFallback(Long doctorId, Exception e) {
        ApiResponseError response = new ApiResponseError(
                "Hiện tại không thể kiểm tra thông tin bác sĩ, vui lòng thử lại sau vài giây",
                503,
                "Doctor Service Error",
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }
}
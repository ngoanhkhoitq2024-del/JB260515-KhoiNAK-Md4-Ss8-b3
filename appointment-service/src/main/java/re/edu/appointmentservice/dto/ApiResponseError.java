package re.edu.appointmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponseError {
    private String message;
    private int status;
    private String error;
    private LocalDateTime timestamp;
}
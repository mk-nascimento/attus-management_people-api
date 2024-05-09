package tec.attus.management.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode()
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}

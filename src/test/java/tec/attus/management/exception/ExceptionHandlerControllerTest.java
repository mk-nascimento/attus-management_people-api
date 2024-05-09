package tec.attus.management.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class ExceptionHandlerControllerTest {
    @Test
    void testHandleHttpException() {
        ExceptionHandlerController controller = new ExceptionHandlerController();
        ResponseEntity<ErrorResponse> response = controller.handleHttpException(new Exception("error message"));

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertEquals("error message", response.getBody().getMessage());
    }
}

package tec.attus.management.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ErrorResponseTest {
    @Test
    void testEqualsAndHashCode() {
        ErrorResponse error1 = new ErrorResponse("err");
        Assertions.assertNotNull(error1.getMessage());
        Assertions.assertEquals(error1, new ErrorResponse("err"));
        Assertions.assertNotEquals(error1, new ErrorResponse("err1"));

        ErrorResponse error2 = new ErrorResponse(null);
        Assertions.assertNotEquals(error1, error2);
        Assertions.assertNull(error2.getMessage());
        Assertions.assertEquals(error2, new ErrorResponse(null));
    }
}

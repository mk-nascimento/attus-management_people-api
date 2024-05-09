package tec.attus.management.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UtilsTest {
    @Test()
    void testUtilsConstructor() {
        try {
            Constructor<Utils> constructor = Utils.class.getDeclaredConstructor();

            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException
                | InvocationTargetException e) {
            assertEquals(IllegalStateException.class, e.getCause().getClass());
        }
    }
}

package app.util;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class MessageUtilsTest {

    @Test
    public void getCodeFromMessage() throws Exception {
        String message = "student.not.found";
        String code = MessageUtils.getMessage(message);
        assertEquals("Student Not Found for Id: ", code);

        message = "success.response";
        code = MessageUtils.getMessage(message);
        assertEquals("Student Registered successfully", code);
    }

}

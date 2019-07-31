package app.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageUtils {
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages");

    public static String getMessage(String messagePattern) {
        try {
            return MESSAGES.getString(messagePattern);
        } catch (MissingResourceException mre) {
            return messagePattern;
        }
    }

    public static String getMessage(String messagePattern, String... parameters) {
        try {
            if (parameters.length == 0) {
                return getMessage(messagePattern);
            }
            return MessageFormat.format(getMessage(messagePattern), (Object[]) parameters);
        } catch (MissingResourceException mre) {
            return messagePattern;
        }
    }
}
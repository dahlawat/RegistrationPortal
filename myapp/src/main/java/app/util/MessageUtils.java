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
}
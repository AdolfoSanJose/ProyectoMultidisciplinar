package com.hospit.app.rest.email.sender.enums;

import lombok.Getter;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code MailCredentialProperties} enum represents properties related to mail credentials.
 * It includes USER and PASSWORD properties, each associated with an ID and a corresponding property name.
 */
@Getter
public enum MailCredentialProperties {

    /**
     * USER property with ID 1 and property name "mail.smtp.user".
     */
    USER(1, "mail.smtp.user"),

    /**
     * PASSWORD property with ID 2 and property name "mail.smtp.password".
     */
    PASSWORD(2, "mail.smtp.password");

    /**
     * The unique identifier for the property.
     */
    private final int id;

    /**
     * The name of the property.
     */
    private final String name;

    /**
     * Constructs a {@code MailCredentialProperties} with the specified ID and property name.
     *
     * @param id   The unique identifier for the property.
     * @param name The name of the property.
     */
    MailCredentialProperties(int id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Compares the provided ID with the enum's ID.
     *
     * @param id The ID to compare.
     * @return {@code true} if the provided ID matches the enum's ID, {@code false} otherwise.
     */
    public boolean compareValue(int id) {
        return this.id == id;
    }

    /**
     * Compares the provided value with the enum's property name.
     *
     * @param valueToCompare The value to compare.
     * @return {@code true} if the provided value matches the enum's property name, {@code false} otherwise.
     */
    public boolean compareValue(String valueToCompare) {
        return this.name.equals(valueToCompare);
    }

    /**
     * Gets the enum constant based on the provided ID.
     *
     * @param id The ID of the desired enum constant.
     * @return The enum constant with the provided ID, or {@code null} if not found.
     */
    public static MailCredentialProperties getValueById(int id) {
        for (MailCredentialProperties mailProp : values()) {
            if (mailProp.getId() == id) {
                return mailProp;
            }
        }
        return null;
    }
}

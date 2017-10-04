package project.command.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleReader {
    private String languageCode = "en-US";
    private ResourceBundle resourceBundle;
    private static ResourceBundleReader instance = new ResourceBundleReader();

    private ResourceBundleReader() {
        initResourceBundle();
    }

    public static ResourceBundleReader getInstance() {
        return instance;
    }

    public String getProperty(String propertyName) {
        return resourceBundle.getString(propertyName);
    }

    public void updateLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        initResourceBundle();
    }

    private void initResourceBundle() {
        resourceBundle = ResourceBundle.getBundle("/localization/labels", Locale.forLanguageTag(languageCode));
    }
}

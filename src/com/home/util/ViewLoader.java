package com.home.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ViewLoader {

    private static URL generateViewResource(String viewPath) {
        String viewSeparator = ".";
        StringBuilder builder = new StringBuilder();
        ClassLoader loader = ViewLoader.class.getClassLoader();
        String systemSeparator = System.getProperty("file.separator");

        builder.append("com");
        builder.append(systemSeparator);
        builder.append("home");
        builder.append(systemSeparator);
        builder.append("view");
        builder.append(systemSeparator);
        builder.append(viewPath.replace(viewSeparator, systemSeparator));
        builder.append(".fxml");

        return loader.getResource(builder.toString());
    }

    public static Parent load(String viewPath) throws IOException {
        URL resource = generateViewResource(viewPath);
        return FXMLLoader.load(resource);
    }
}

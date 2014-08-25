package sample;

import java.util.*;

public class Bundle_resource extends ListResourceBundle {

    String[][] content;

    public Bundle_resource() {
        content = new String[][] {{"username_txt", "Użytkownik"},
                {"password_txt", "Hasło"},
                {"key4", "Sign In"},
                {"key5", "Sign in button pressed"},
                {"key10", "Portuguese"},
                {"key11", "English"},
                {"sample_name", "sample_name"}};
    }

    @Override
    protected Object[][] getContents() {
        return content;
    }}

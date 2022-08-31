module com.blueblazes13.senjoassist {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires  java.base;
    requires  java.sql;
    requires transitive javafx.graphics;
    
    opens com.blueblazes13.senjoassist to javafx.fxml;
    opens com.blueblazes13.senjoassist.model to com.google.gson;
    
    exports com.blueblazes13.senjoassist;
    exports com.blueblazes13.senjoassist.model;
    exports com.blueblazes13.senjoassist.view;
    requires yetanotherhueapi;
    requires spotify.web.api.java;
    requires org.slf4j;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires jlayer;
    requires com.google.gson;
}

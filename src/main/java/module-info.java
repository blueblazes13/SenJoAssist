module com.blueblazes13.senjoassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens com.blueblazes13.senjoassist to javafx.fxml;
    opens com.blueblazes13.senjoassist.model to com.google.gson;
    
    exports com.blueblazes13.senjoassist;
    exports com.blueblazes13.senjoassist.model;
    requires yetanotherhueapi;
    requires spotify.web.api.java;
    requires org.slf4j;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires jlayer;
    requires com.google.gson;
}

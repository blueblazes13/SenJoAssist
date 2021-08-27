module com.blueblazes13.senjoassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens com.blueblazes13.senjoassist to javafx.fxml;
    
    exports com.blueblazes13.senjoassist;
    requires yetanotherhueapi;
    requires spotify.web.api.java;
    requires org.slf4j;
    requires org.slf4j.simple;
    requires org.apache.httpcomponents.core5.httpcore5;
    requires jlayer;
}

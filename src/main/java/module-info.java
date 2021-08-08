module com.blueblazes13.senjoassist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.blueblazes13.senjoassist to javafx.fxml;
    
    exports com.blueblazes13.senjoassist;
    requires yetanotherhueapi;
    
}

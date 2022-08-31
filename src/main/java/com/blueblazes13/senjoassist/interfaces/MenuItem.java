package com.blueblazes13.senjoassist.interfaces;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public interface MenuItem {

    public abstract Node getView();
    public abstract void onMouseClick(MouseEvent me);
    public abstract Node getSymbol();

}

package com.jukusoft.erp.gui.javafx;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public interface Window {

    /**
    * get stage of window
     *
     * @return instance of stage of window
    */
    public Stage getStage ();

    /**
    * get root pane of window
     *
     * @return root pane of window
    */
    public Pane getRootPane ();

    /**
    * check, if window is visible
     *
     * @return true, if window is visible
    */
    public boolean isVisible ();

    /**
    * show or hide window
     *
     * @param visible visible flag (true, if window should be shown)
    */
    public void setVisible (boolean visible);

    public String getTitle ();

    public void setTitle (String title);

    public boolean isMaximized ();

    public void setMaximized (boolean maximized);

    public boolean isFullscreen ();

    public void setFullscreen (boolean fullscreen);

    public void addOnCloseRequestHandler (EventHandler<WindowEvent> value);

    public void removeOnCloseRequestHandler (EventHandler<WindowEvent> event);

    public long getWindowID ();

    public void setWindowID (long windowID);

}

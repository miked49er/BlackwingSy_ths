package controller;

import com.sun.istack.internal.Nullable;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaException;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import model.*;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Controller
 * @author BlackwingSy_ths
 * @version 1.0
 *
 * Description: FX Controls
 */
public class Controller implements Initializable {

    @FXML
    protected Label error;
    @FXML
    protected Button N1btn;
    @FXML
    protected Button N2btn;
    @FXML
    protected Button N3btn;
    @FXML
    protected Button N4btn;
    @FXML
    protected Button N5btn;
    @FXML
    protected Button N6btn;
    @FXML
    protected Button N7btn;
    @FXML
    protected Button N8btn;
    @FXML
    protected Button N9btn;
    @FXML
    protected Button N0btn;
    @FXML
    protected Button Qbtn;
    @FXML
    protected Button Wbtn;
    @FXML
    protected Button Ebtn;
    @FXML
    protected Button Rbtn;
    @FXML
    protected Button Tbtn;
    @FXML
    protected Button Ybtn;
    @FXML
    protected Button Ubtn;
    @FXML
    protected Button Ibtn;
    @FXML
    protected Button Obtn;
    @FXML
    protected Button Pbtn;
    @FXML
    protected Button Abtn;
    @FXML
    protected Button Sbtn;
    @FXML
    protected Button Dbtn;
    @FXML
    protected Button Fbtn;
    @FXML
    protected Button Gbtn;
    @FXML
    protected Button Hbtn;
    @FXML
    protected Button Jbtn;
    @FXML
    protected Button Kbtn;
    @FXML
    protected Button Lbtn;
    @FXML
    protected Button Zbtn;
    @FXML
    protected Button Xbtn;
    @FXML
    protected Button Cbtn;
    @FXML
    protected Button Vbtn;
    @FXML
    protected Button Bbtn;
    @FXML
    protected Button Nbtn;
    @FXML
    protected Button Mbtn;

    @FXML
    protected Pane gui;
    @FXML
    protected MenuItem playStopToggle;
    protected boolean tapHoldToggle;
    private final boolean TAP = true;
    private final boolean HOLD = false;
    private HostServices hostServices;
    protected ArrayList< Button > btns;
    protected Data data;
    protected static ArrayList< String > hatP;
    protected static ArrayList< String > kickP;
    protected static ArrayList< String > snareP;
    protected static ArrayList< String > bLoopP;
    protected static ArrayList< String > dLoopP;
    protected static ArrayList< String > pLoopP;
    protected static ArrayList< String > sfxP;
    protected static ArrayList< String > vocalP;
    protected static ArrayList< String > allP;

    @Override
    public void initialize( URL location, ResourceBundle resources ) {

        System.out.println("View is now loaded!");
        error.setText("");
        this.tapHoldToggle = HOLD;

        try {
            data = new Data();
            this.hatP = data.hatP;
            this.kickP = data.kickP;
            this.snareP = data.snareP;
            this.bLoopP = data.bLoopP;
            this.dLoopP = data.dLoopP;
            this.pLoopP = data.pLoopP;
            this.sfxP = data.sfxP;
            this.vocalP = data.vocalP;
            this.allP = data.allP;
        }
        catch ( Exception e ) {
            System.out.println("Data Error");
            e.printStackTrace();
        }

        btns = new ArrayList< Button >();

        btns.add(N1btn);
        btns.add(N2btn);
        btns.add(N3btn);
        btns.add(N4btn);
        btns.add(N5btn);
        btns.add(N6btn);
        btns.add(N7btn);
        btns.add(N8btn);
        btns.add(N9btn);
        btns.add(N0btn);
        btns.add(Qbtn);
        btns.add(Wbtn);
        btns.add(Ebtn);
        btns.add(Rbtn);
        btns.add(Tbtn);
        btns.add(Ybtn);
        btns.add(Ubtn);
        btns.add(Ibtn);
        btns.add(Obtn);
        btns.add(Pbtn);
        btns.add(Abtn);
        btns.add(Sbtn);
        btns.add(Dbtn);
        btns.add(Fbtn);
        btns.add(Gbtn);
        btns.add(Hbtn);
        btns.add(Jbtn);
        btns.add(Kbtn);
        btns.add(Lbtn);
        btns.add(Zbtn);
        btns.add(Xbtn);
        btns.add(Cbtn);
        btns.add(Vbtn);
        btns.add(Bbtn);
        btns.add(Nbtn);
        btns.add(Mbtn);

        for ( int i = 0; i < btns.size(); i++ ) {
            setupContextMenu(btns.get(i));
        }
    }

    /**
     * setHostServices
     * @param hostServices HostServices
     */
    public void setHostServices( HostServices hostServices ) {
        this.hostServices = hostServices;
    }

    private File openDialog( String title, @Nullable ArrayList< FileChooser.ExtensionFilter > filters, @Nullable String fileExtention, @Nullable String initDir ) throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        if ( filters != null ) {
            fileChooser.getExtensionFilters().addAll(filters);
        }

        if ( initDir != null ) {
            fileChooser.setInitialDirectory(new File(initDir));
        }

        File file = fileChooser.showOpenDialog(null);

        if ( file != null && fileExtention != null ) {
            if ( file.getName().endsWith(fileExtention) ) {
                return file;
            }
            else {
                throw new FileNotFoundException(file.getName() + " does not have a valid file-extension");
            }
        }
        return null;
    }

    private File saveDialog( String title, @Nullable ArrayList< FileChooser.ExtensionFilter > filters, @Nullable String initFileName, @Nullable String initDir ) throws FileNotFoundException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        if ( filters != null ) {
            fileChooser.getExtensionFilters().addAll(filters);
        }

        if ( initFileName != null ) {
            fileChooser.setInitialFileName(initFileName);
        }

        if ( initDir != null ) {
            fileChooser.setInitialDirectory(new File(initDir));
        }

        File file = fileChooser.showSaveDialog(null);

        if ( file != null ) {
            if ( file.getName().endsWith(initFileName.substring(1)) ) {
                return file;
            }
            else {
                throw new FileNotFoundException(file.getName() + " does not have a valid file-extension");
            }
        }
        return null;
    }

    /**
     * assignMusic
     * @param event MouseEvent
     * select the music file to assign to button
     */
    @FXML
    protected void assignMusic( MouseEvent event ) {

        if ( event.getButton().equals(MouseButton.PRIMARY) ) {

            Button btn = (Button) event.getSource();
            ;

            ArrayList< FileChooser.ExtensionFilter > filters = new ArrayList<>();
            filters.add(new FileChooser.ExtensionFilter("All Music", "*.*"));
            filters.add(new FileChooser.ExtensionFilter("WAV", "*.wav", "*.wave"));
            filters.add(new FileChooser.ExtensionFilter("MP4", "*.mp4"));
            filters.add(new FileChooser.ExtensionFilter("MP3", "*.mp3"));

            try {
                File file = openDialog("Select Music Bite", filters, null, null);
                btn.setSound(file);
                btn.getStyleClass().add("white");
                btn.getStyleClass().remove("medium-bg");
                this.error.setText("");
            }
            catch ( NullPointerException npe ) {
                this.error.setText("No File Selected");
            }
            catch ( MediaException me ) {
                this.error.setText("Unsupported File Type");
            }
            catch ( FileNotFoundException fnf ) {
                error.setText(fnf.getMessage());
            }
        }
    }

    /**
     * reset
     * reset the application
     */
    @FXML
    protected void reset( ActionEvent event ) {

        for ( int i = 0; i < this.btns.size(); i++ ) {

            Button btn = this.btns.get(i);
            btn.unbindSound();
            btn.getStyleClass().remove(1, btn.getStyleClass().size());
            btn.getStyleClass().add("medium-bg");
        }
    }

    /**
     * playMusic
     * @param btn Button
     * Start playback of sound
     */
    protected void playMusic( final Button btn ) {

        btn.playSound();
        if ( btn.isPlaying() ) {
            btn.getSound().setOnEndOfMedia(null);
            if ( !btn.getStyleClass().contains("active") ) {

                btn.getStyleClass().add("active");
            }
        }
    }

    /**
     * stopMusic
     * @param btn Button
     * Stop playback of sound
     */
    protected void stopMusic( Button btn ) {

        btn.stopSound();
        btn.getStyleClass().remove("active");
    }

    /**
     * stopAll
     * stop all of the sounds playing
     * remove any end of media events
     */
    protected void stopAll() {

        for ( int i = 0; i < btns.size(); i++ ) {

            Button btn = btns.get(i);
            if ( btn.getSound() != null ) {
                btn.stopSound();
                btn.getSound().setOnEndOfMedia(null);
            }
        }
    }

    /**
     * toggleMusic
     * @param btn
     * Toggle playback of sound
     */
    protected void toggleMusic( final Button btn ) {

        if ( btn.isPlaying() ) {
            btn.stopSound();
            btn.getStyleClass().remove("active");
        }
        else {
            btn.playSound();
            if ( btn.isPlaying() ) {
                btn.getSound().setOnEndOfMedia(new Runnable() {
                    public void run() {
                        btn.stopSound();
                        btn.getStyleClass().remove("active");
                    }
                });
                if ( !btn.getStyleClass().contains("active") ) {

                    btn.getStyleClass().add("active");
                }
            }
        }
    }

    /**
     * playMusicEvent
     * @param event KeyEvent
     * Play music based on key press
     */
    @FXML
    protected void playMusicEvent( KeyEvent event ) {

        switch ( event.getCode() ) {
            case DIGIT1:
                playMusic(N1btn);
                break;
            case DIGIT2:
                playMusic(N2btn);
                break;
            case DIGIT3:
                playMusic(N3btn);
                break;
            case DIGIT4:
                playMusic(N4btn);
                break;
            case DIGIT5:
                playMusic(N5btn);
                break;
            case DIGIT6:
                playMusic(N6btn);
                break;
            case DIGIT7:
                playMusic(N7btn);
                break;
            case DIGIT8:
                playMusic(N8btn);
                break;
            case DIGIT9:
                playMusic(N9btn);
                break;
            case DIGIT0:
                playMusic(N0btn);
                break;
            case NUMPAD1:
                playMusic(N1btn);
                break;
            case NUMPAD2:
                playMusic(N2btn);
                break;
            case NUMPAD3:
                playMusic(N3btn);
                break;
            case NUMPAD4:
                playMusic(N4btn);
                break;
            case NUMPAD5:
                playMusic(N5btn);
                break;
            case NUMPAD6:
                playMusic(N6btn);
                break;
            case NUMPAD7:
                playMusic(N7btn);
                break;
            case NUMPAD8:
                playMusic(N8btn);
                break;
            case NUMPAD9:
                playMusic(N9btn);
                break;
            case NUMPAD0:
                playMusic(N0btn);
                break;
            case A:
                playMusic(Abtn);
                break;
            case B:
                playMusic(Bbtn);
                break;
            case C:
                playMusic(Cbtn);
                break;
            case D:
                playMusic(Dbtn);
                break;
            case E:
                playMusic(Ebtn);
                break;
            case F:
                playMusic(Fbtn);
                break;
            case G:
                playMusic(Gbtn);
                break;
            case H:
                playMusic(Hbtn);
                break;
            case I:
                playMusic(Ibtn);
                break;
            case J:
                playMusic(Jbtn);
                break;
            case K:
                playMusic(Kbtn);
                break;
            case L:
                playMusic(Lbtn);
                break;
            case M:
                playMusic(Mbtn);
                break;
            case N:
                playMusic(Nbtn);
                break;
            case O:
                playMusic(Obtn);
                break;
            case P:
                playMusic(Pbtn);
                break;
            case Q:
                playMusic(Qbtn);
                break;
            case R:
                playMusic(Rbtn);
                break;
            case S:
                playMusic(Sbtn);
                break;
            case T:
                playMusic(Tbtn);
                break;
            case U:
                playMusic(Ubtn);
                break;
            case V:
                playMusic(Vbtn);
                break;
            case W:
                playMusic(Wbtn);
                break;
            case X:
                playMusic(Xbtn);
                break;
            case Y:
                playMusic(Ybtn);
                break;
            case Z:
                playMusic(Zbtn);
                break;
            default:
                break;
        }
    }

    /**
     * stopMusicEvent
     * @param event KeyEvent
     * Stop music based on key press
     */
    @FXML
    protected void stopMusicEvent( KeyEvent event ) {

        switch ( event.getCode() ) {
            case DIGIT1:
                stopMusic(N1btn);
                break;
            case DIGIT2:
                stopMusic(N2btn);
                break;
            case DIGIT3:
                stopMusic(N3btn);
                break;
            case DIGIT4:
                stopMusic(N4btn);
                break;
            case DIGIT5:
                stopMusic(N5btn);
                break;
            case DIGIT6:
                stopMusic(N6btn);
                break;
            case DIGIT7:
                stopMusic(N7btn);
                break;
            case DIGIT8:
                stopMusic(N8btn);
                break;
            case DIGIT9:
                stopMusic(N9btn);
                break;
            case DIGIT0:
                stopMusic(N0btn);
                break;
            case NUMPAD1:
                stopMusic(N1btn);
                break;
            case NUMPAD2:
                stopMusic(N2btn);
                break;
            case NUMPAD3:
                stopMusic(N3btn);
                break;
            case NUMPAD4:
                stopMusic(N4btn);
                break;
            case NUMPAD5:
                stopMusic(N5btn);
                break;
            case NUMPAD6:
                stopMusic(N6btn);
                break;
            case NUMPAD7:
                stopMusic(N7btn);
                break;
            case NUMPAD8:
                stopMusic(N8btn);
                break;
            case NUMPAD9:
                stopMusic(N9btn);
                break;
            case NUMPAD0:
                stopMusic(N0btn);
                break;
            case A:
                stopMusic(Abtn);
                break;
            case B:
                stopMusic(Bbtn);
                break;
            case C:
                stopMusic(Cbtn);
                break;
            case D:
                stopMusic(Dbtn);
                break;
            case E:
                stopMusic(Ebtn);
                break;
            case F:
                stopMusic(Fbtn);
                break;
            case G:
                stopMusic(Gbtn);
                break;
            case H:
                stopMusic(Hbtn);
                break;
            case I:
                stopMusic(Ibtn);
                break;
            case J:
                stopMusic(Jbtn);
                break;
            case K:
                stopMusic(Kbtn);
                break;
            case L:
                stopMusic(Lbtn);
                break;
            case M:
                stopMusic(Mbtn);
                break;
            case N:
                stopMusic(Nbtn);
                break;
            case O:
                stopMusic(Obtn);
                break;
            case P:
                stopMusic(Pbtn);
                break;
            case Q:
                stopMusic(Qbtn);
                break;
            case R:
                stopMusic(Rbtn);
                break;
            case S:
                stopMusic(Sbtn);
                break;
            case T:
                stopMusic(Tbtn);
                break;
            case U:
                stopMusic(Ubtn);
                break;
            case V:
                stopMusic(Vbtn);
                break;
            case W:
                stopMusic(Wbtn);
                break;
            case X:
                stopMusic(Xbtn);
                break;
            case Y:
                stopMusic(Ybtn);
                break;
            case Z:
                stopMusic(Zbtn);
                break;
            default:
                break;
        }
    }

    /**
     * toggleMusicEvent
     * @param event KeyEvent
     * Play music based on key press
     */
    @FXML
    protected void toggleMusicEvent( KeyEvent event ) {

        switch ( event.getCode() ) {
            case DIGIT1:
                toggleMusic(N1btn);
                break;
            case DIGIT2:
                toggleMusic(N2btn);
                break;
            case DIGIT3:
                toggleMusic(N3btn);
                break;
            case DIGIT4:
                toggleMusic(N4btn);
                break;
            case DIGIT5:
                toggleMusic(N5btn);
                break;
            case DIGIT6:
                toggleMusic(N6btn);
                break;
            case DIGIT7:
                toggleMusic(N7btn);
                break;
            case DIGIT8:
                toggleMusic(N8btn);
                break;
            case DIGIT9:
                toggleMusic(N9btn);
                break;
            case DIGIT0:
                toggleMusic(N0btn);
                break;
            case NUMPAD1:
                toggleMusic(N1btn);
                break;
            case NUMPAD2:
                toggleMusic(N2btn);
                break;
            case NUMPAD3:
                toggleMusic(N3btn);
                break;
            case NUMPAD4:
                toggleMusic(N4btn);
                break;
            case NUMPAD5:
                toggleMusic(N5btn);
                break;
            case NUMPAD6:
                toggleMusic(N6btn);
                break;
            case NUMPAD7:
                toggleMusic(N7btn);
                break;
            case NUMPAD8:
                toggleMusic(N8btn);
                break;
            case NUMPAD9:
                toggleMusic(N9btn);
                break;
            case NUMPAD0:
                toggleMusic(N0btn);
                break;
            case A:
                toggleMusic(Abtn);
                break;
            case B:
                toggleMusic(Bbtn);
                break;
            case C:
                toggleMusic(Cbtn);
                break;
            case D:
                toggleMusic(Dbtn);
                break;
            case E:
                toggleMusic(Ebtn);
                break;
            case F:
                toggleMusic(Fbtn);
                break;
            case G:
                toggleMusic(Gbtn);
                break;
            case H:
                toggleMusic(Hbtn);
                break;
            case I:
                toggleMusic(Ibtn);
                break;
            case J:
                toggleMusic(Jbtn);
                break;
            case K:
                toggleMusic(Kbtn);
                break;
            case L:
                toggleMusic(Lbtn);
                break;
            case M:
                toggleMusic(Mbtn);
                break;
            case N:
                toggleMusic(Nbtn);
                break;
            case O:
                toggleMusic(Obtn);
                break;
            case P:
                toggleMusic(Pbtn);
                break;
            case Q:
                toggleMusic(Qbtn);
                break;
            case R:
                toggleMusic(Rbtn);
                break;
            case S:
                toggleMusic(Sbtn);
                break;
            case T:
                toggleMusic(Tbtn);
                break;
            case U:
                toggleMusic(Ubtn);
                break;
            case V:
                toggleMusic(Vbtn);
                break;
            case W:
                toggleMusic(Wbtn);
                break;
            case X:
                toggleMusic(Xbtn);
                break;
            case Y:
                toggleMusic(Ybtn);
                break;
            case Z:
                toggleMusic(Zbtn);
                break;
            default:
                break;
        }
    }

    @FXML
    protected void toggleTapHold( ActionEvent event ) {
//        System.out.println(event);

        if ( tapHoldToggle == HOLD ) {

            tapHoldToggle = TAP;
            playStopToggle.setText("Hold to Play");
            stopAll();
            gui.setOnKeyPressed(new EventHandler< KeyEvent >() {
                @Override
                public void handle( KeyEvent event ) {
                    toggleMusicEvent(event);
                }
            });
            gui.setOnKeyReleased(null);
        }
        else {

            tapHoldToggle = HOLD;
            playStopToggle.setText("Tap to Play");
            gui.setOnKeyPressed(new EventHandler< KeyEvent >() {
                @Override
                public void handle( KeyEvent event ) {
                    playMusicEvent(event);
                }
            });
            gui.setOnKeyReleased(new EventHandler< KeyEvent >() {
                @Override
                public void handle( KeyEvent event ) {
                    stopMusicEvent(event);
                }
            });
        }
    }

    /**
     * styleButton
     * @param btn Button
     * @param event ActionEvent
     * Change the background color of the button
     */
    @FXML
    protected void styleButton( Button btn, ActionEvent event ) {

        // Retrieve the MenuItem clicked on
        MenuItem item = (MenuItem) event.getSource();

        // Retrieve the css class from the circle on the menuitem
        String newClass = item.getGraphic().getStyleClass().get(item.getGraphic().getStyleClass().size() - 1);

        // Replace old class with newClass to btn
        btn.getStyleClass().remove(btn.getStyleClass().get(btn.getStyleClass().size() - 1));
        btn.getStyleClass().add(newClass);
    }

    //Create sound menu button
    @FXML
    protected void soundButton( Button btn, ActionEvent event ) {

        // Retrieve the MenuItem clicked on
        MenuItem item = (MenuItem) event.getSource();

        try {
            btn.setSound(item.getFile());
            btn.getStyleClass().add("white");
            btn.getStyleClass().remove("medium-bg");
            this.error.setText("");
        }
        catch ( NullPointerException npe ) {
            this.error.setText("No File Selected");
        }
        catch ( MediaException me ) {
            this.error.setText("Unsupported File Type");
            System.out.println(item.getFile());
            me.printStackTrace();
        }
    }

    /**
     * clearColors
     * @param event ActionEvent
     * Reset all button colors to default
     * medium-bg for unassigned buttons
     * white for assigned buttons
     */
    @FXML
    protected void clearColors( ActionEvent event ) {

        for ( int i = 0; i < btns.size(); i++ ) {

            Button btn = btns.get(i);
            btn.getStyleClass().remove(btn.getStyleClass().size() - 1);

            if ( btn.getSound() != null ) {

                btn.getStyleClass().add("white");
            }
            else {

                btn.getStyleClass().add("medium-bg");
            }
        }
    }

    /**
     * setupContextMenu
     * @param btn Button
     * Create a context menu for btn
     */
    protected void setupContextMenu( Button btn ) {
        final ContextMenu menu = new ContextMenu();

        // Create a sub-menu for the colors
        Menu colors = new Menu("Colors");

        // Create a sub-menu for the sounds
        Menu sounds = new Menu("Sounds");

        // Create sub-menus for Drums, Loops, Vocals, and Sound Effects
        Menu drums = new Menu("Drums");
        Menu loops = new Menu("Loops");
        Menu vocals = new Menu("Vocals");
        Menu sEffects = new Menu("Sound Effects");

        // Create sub-menus for Drums
        Menu dHat = new Menu("Hat");
        Menu dKick = new Menu("Kick");
        Menu dSnare = new Menu("Snare");

        // Create sub-menus for Loops
        Menu bLoop = new Menu("Bass");
        Menu dLoop = new Menu("Drum");
        Menu pLoop = new Menu("Piano");

        // Add items to the sub-menu
        sounds.getItems().add(drums);
        sounds.getItems().add(loops);
        sounds.getItems().add(vocals);
        sounds.getItems().add(sEffects);

        // Add sub-menus to Drums
        drums.getItems().add(dHat);
        drums.getItems().add(dKick);
        drums.getItems().add(dSnare);

        // Add sub-menus to Loops
        loops.getItems().add(bLoop);
        loops.getItems().add(dLoop);
        loops.getItems().add(pLoop);

        // Add the menu items to the sub-menu
        colors.getItems().add(colorMenuItem(btn, "red"));
        colors.getItems().add(colorMenuItem(btn, "orange"));
        colors.getItems().add(colorMenuItem(btn, "yellow"));
        colors.getItems().add(colorMenuItem(btn, "green"));
        colors.getItems().add(colorMenuItem(btn, "blue"));
        colors.getItems().add(colorMenuItem(btn, "teal"));
        colors.getItems().add(colorMenuItem(btn, "violet"));
        colors.getItems().add(colorMenuItem(btn, "white"));

        // Add the drum menu items to the sub-menu
        for ( int i = 0; i < hatP.size(); i++ ) {
            dHat.getItems().add(soundMenuItem(btn, ( hatP.get(i) )));
        }
        for ( int i = 0; i < kickP.size(); i++ ) {
            dKick.getItems().add(soundMenuItem(btn, ( kickP.get(i) )));
        }
        for ( int i = 0; i < snareP.size(); i++ ) {
            dSnare.getItems().add(soundMenuItem(btn, ( snareP.get(i) )));
        }
        // Add the loop menu items to the sub-menu
        for ( int i = 0; i < bLoopP.size(); i++ ) {
            bLoop.getItems().add(soundMenuItem(btn, ( bLoopP.get(i) )));
        }
        for ( int i = 0; i < dLoopP.size(); i++ ) {
            dLoop.getItems().add(soundMenuItem(btn, ( dLoopP.get(i) )));
        }
        for ( int i = 0; i < pLoopP.size(); i++ ) {
            pLoop.getItems().add(soundMenuItem(btn, ( pLoopP.get(i) )));
        }
        // Add the SFX menu items to the sub-menu
        for ( int i = 0; i < sfxP.size(); i++ ) {
            sEffects.getItems().add(soundMenuItem(btn, ( sfxP.get(i) )));
        }
        // Add the vocal menu items to the sub-menu
        for ( int i = 0; i < vocalP.size(); i++ ) {
            vocals.getItems().add(soundMenuItem(btn, ( vocalP.get(i) )));
        }

        // Add the sub-menu to the context menu
        menu.getItems().add(colors);
        menu.getItems().add(sounds);

        // Assign the context menu to btn
        btn.setContextMenu(menu);
    }

    /**
     * colorMenuItem
     * @param btn Button
     * @param str String
     * @return item MenuItem
     * Creates a the menu item for the colors sub-menu
     * Creates an event handler for the menu item
     */
    protected MenuItem colorMenuItem( final Button btn, String str ) {

        // Capitalize the first letter of the string
        str = str.toLowerCase();
        str = Character.toString(str.charAt(0)).toUpperCase() + str.substring(1);

        // Circle to display the color
        Circle circle = new Circle();
        circle.setRadius(6);
        circle.getStyleClass().add(str.toLowerCase());

        // Create the MenuItem and create an event handler
        MenuItem item = new MenuItem(str, circle);
        item.setOnAction(new EventHandler< ActionEvent >() {
            public void handle( ActionEvent event ) {
                styleButton(btn, event);
            }
        });

        return item;
    }

    /**
     * Create sound menu items with event handler
     */
    protected MenuItem soundMenuItem( final Button btn, String str ) {

        // Create the MenuItem and create an event handler
        File file = new File(str);
        String name = file.getName().substring(0, file.getName().indexOf('.'));
        if ( name.contains("%") ) {
            name = name.substring(0, name.indexOf('%')) + " " + name.substring(name.indexOf('%') + 3);
        }
        MenuItem item = new MenuItem(name);
        item.setFile(file);
        item.setOnAction(new EventHandler< ActionEvent >() {
            public void handle( ActionEvent event ) {
                soundButton(btn, event);
            }
        });
        return item;
    }

    /**
     * helpBtn
     * Redirect the user to our help page
     */
    @FXML
    protected void helpBtn( ActionEvent event ) {
        hostServices.showDocument("http://syths.io");
    }

    protected Button findButton( String key ) {

        Button btn = null;

        switch ( key ) {
            case "1btn":
                btn = N1btn;
                break;
            case "2btn":
                btn = N2btn;
                break;
            case "3btn":
                btn = N3btn;
                break;
            case "4btn":
                btn = N4btn;
                break;
            case "5btn":
                btn = N5btn;
                break;
            case "6btn":
                btn = N6btn;
                break;
            case "7btn":
                btn = N7btn;
                break;
            case "8btn":
                btn = N8btn;
                break;
            case "9btn":
                btn = N9btn;
                break;
            case "0btn":
                btn = N0btn;
                break;
            case "Qbtn":
                btn = Qbtn;
                break;
            case "Wbtn":
                btn = Wbtn;
                break;
            case "Ebtn":
                btn = Ebtn;
                break;
            case "Rbtn":
                btn = Rbtn;
                break;
            case "Tbtn":
                btn = Tbtn;
                break;
            case "Ybtn":
                btn = Ybtn;
                break;
            case "Ubtn":
                btn = Ubtn;
                break;
            case "Ibtn":
                btn = Ibtn;
                break;
            case "Obtn":
                btn = Obtn;
                break;
            case "Pbtn":
                btn = Pbtn;
                break;
            case "Abtn":
                btn = Abtn;
                break;
            case "Sbtn":
                btn = Sbtn;
                break;
            case "Dbtn":
                btn = Dbtn;
                break;
            case "Fbtn":
                btn = Fbtn;
                break;
            case "Gbtn":
                btn = Gbtn;
                break;
            case "Hbtn":
                btn = Hbtn;
                break;
            case "Jbtn":
                btn = Jbtn;
                break;
            case "Kbtn":
                btn = Kbtn;
                break;
            case "Lbtn":
                btn = Lbtn;
                break;
            case "Zbtn":
                btn = Zbtn;
                break;
            case "Xbtn":
                btn = Xbtn;
                break;
            case "Cbtn":
                btn = Cbtn;
                break;
            case "Vbtn":
                btn = Vbtn;
                break;
            case "Bbtn":
                btn = Bbtn;
                break;
            case "Nbtn":
                btn = Nbtn;
                break;
            case "Mbtn":
                btn = Mbtn;
                break;
        }

        return btn;
    }

    @FXML
    protected void save( ActionEvent event ) {

        ArrayList< String > out = new ArrayList<>();

        for ( int i = 0; i < btns.size(); i++ ) {

            String str = btns.get(i).toString();
            if ( !str.isEmpty() ) {
                out.add(str);
            }
        }

        ArrayList< FileChooser.ExtensionFilter > fe = new ArrayList<>();
        fe.add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));

        try {
            WriteFile writer = new WriteFile(out, saveDialog("Save Key Bindings", fe, "*.txt", System.getProperty("user.home")));
        }
        catch ( NullPointerException npe ) {
            error.setText("No File Selected");
        }
        catch ( FileNotFoundException fnf ) {
            error.setText(fnf.getMessage());
        }
    }

    @FXML
    protected void open( ActionEvent event ) {

        ArrayList< String > fileContents = new ArrayList<>();
        ArrayList< FileChooser.ExtensionFilter > fe = new ArrayList<>();
        fe.add(new FileChooser.ExtensionFilter("Text doc(*.txt)", "*.txt"));

        try {
            File file = openDialog("Open Key Bindings", fe, ".txt", System.getProperty("user.home"));
            ReadFile reader = new ReadFile();
            reader.readFile(file);
            fileContents = reader.getFileList();
        }
        catch ( NullPointerException npe ) {
            error.setText("No File Selected");
        }
        catch ( FileNotFoundException fnf ) {
            error.setText(fnf.getMessage());
        }

        if ( !fileContents.isEmpty() ) {

            reset(new ActionEvent());

            for ( int i = 0; i < fileContents.size(); i++ ) {

                String str = fileContents.get(i);
                String key = str.substring(0, 1) + "btn";
                str = str.substring(2);
                File file = null;

                if ( str.contains("file:") ) {

                    file = new File(str.substring(0, str.indexOf(' ')));
                    str = str.substring(str.indexOf(' ') + 1);
                }

                ArrayList< String > style = new ArrayList<>();
                while ( !str.isEmpty() ) {
                    String sClass = "";
                    if ( str.contains(" ") ) {
                        sClass = str.substring(0, str.indexOf(' '));
                        str = str.substring(str.indexOf(' ') + 1);
                    }
                    else {
                        sClass = str;
                        str = "";
                    }
                    style.add(sClass);
                }

                Button btn = findButton(key);
                if ( btn != null ) {
                    if ( file != null ) {
                        btn.setSound(file);
                    }
                    btn.getStyleClass().remove(btn.getStyleClass().size() - 1);
                    btn.getStyleClass().addAll(style);
                }
            }
        }
    }

}

package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;

public class Button extends javafx.scene.control.Button {

    private MediaPlayer sound;
    private boolean playing;

    public Button() {
        this.sound = null;
        this.playing = false;
    }

    public Button( String text ) {
        super(text);
        this.sound = null;
        this.playing = false;
    }

    public void setSound( String str ) {
        URL path = Button.class.getResource(str);
        File soundFile = new File(path.getPath());
        this.sound = new MediaPlayer(new Media(soundFile.toURI().toString()));
    }

    public void setSound( File file ) {
        this.sound = new MediaPlayer(new Media(file.toURI().toString()));
    }

    public void setSound( Media media ) {
        this.sound = new MediaPlayer(media);

    }

    public MediaPlayer getSound() {
        return sound;
    }

    public void playSound() {
        if ( sound != null ) {
            sound.play();
            playing = true;
        }
    }

    public void stopSound() {
        if ( sound != null ) {
            sound.stop();
            playing = false;
        }
    }

    public boolean isPlaying() {
        return playing;
    }
    
    public void unbindSound() {
    	this.sound = null;
    }

    public String toString() {

        String str = getText();

        if (getSound() != null) {
            str+= " " + getSound().getMedia().getSource();
        }

        if (getStyleClass().toString().substring(7) != "" && !getStyleClass().toString().substring(7).equals("medium-bg")) {
            str+= " " + getStyleClass().toString().substring(7);
        }

        if (!str.equals(getText())) {
            return str;
        }
        return "";
    }

}

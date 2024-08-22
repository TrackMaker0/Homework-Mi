package com.example.homeworkday04;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameBean {
    int gameIcon;
    String gameName;
    String gameStatus;

    public GameBean() {

    }

    public GameBean(int gameIcon, String gameName, String gameStatus) {
        this.gameIcon = gameIcon;
        this.gameName = gameName;
        this.gameStatus = gameStatus;
    }

    public int getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(int gameIcon) {
        this.gameIcon = gameIcon;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }
}

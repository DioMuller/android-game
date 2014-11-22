package com.diogomuller.tensecondheroes.game;

/**
 * Created by Diogo on 22/11/2014.
 */
public class HighscoreInfo {
    private int game;
    private String gameName;
    private int score;

    public HighscoreInfo(int game, int score){
        this.game = game;

        if( this.game == -1){
            this.gameName = "Main Game";
        } else {
            this.gameName = Minigames.getInfo(this.game).getName();
        }

        this.score = score;
    }

    public int getGameId(){ return game; }
    public String getGameName(){ return gameName; }
    public int getScore() { return score; }

    @Override
    public String toString() {
        return "[" + String.format("%03d", score) + "]" + " - " + gameName;
    }
}

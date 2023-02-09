package edu.touro.cs.mcon364;
import java.util.ArrayList;


public class T3Controller {
    T3 model;
    T3view view;

    T3Controller(T3 model, T3view view) {
        this.model = model;
        this.view = view;
    }

    public void setRequest(ArrayList<Integer> position) {
        model.makeMove(position.get(0), position.get(1));
    }

    public String receiveInfo() {
        return model.currentPlayer().toString();
    }

    public String receiveStatus() {
        return model.nextPlayer().toString();
    }

    public boolean receiveTie() {
        return model.isTie();
    }

    public boolean receiveWinner() {
        if (receiveTie()) {
            return false;
        }
        return model.isGameOver();
    }

    public ArrayList<Integer> recWinnerLocal() {
        return model.getWinningLocation();
    }

    public void setRequest() {
        model.reset();
        view.resetView();
    }

}

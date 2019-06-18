package mum.edu.swe.trailerrentalserver.observe;


import mum.edu.swe.trailerrentalserver.command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();
    private ICommand command;

    public void setState(ICommand command) {
        this.command = command;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

package xxl.core;

import java.util.ArrayList;

public class ConcreteSubject implements Subject {
    private static ConcreteSubject _instance = null;
    private ArrayList<Observer> _observers = new ArrayList<Observer>();
    private boolean _stateChanged = false;

    private ConcreteSubject(){
    }

    public static ConcreteSubject getInstance() {
        return _instance == null ? _instance = new ConcreteSubject() : _instance;
    }

    public void addObserver(Observer observer) {
        _observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        _observers.remove(observer);
    }

    public void setStateChanged(boolean stateChanged) {
        _stateChanged = stateChanged;
    }

    public void notifyObservers() {
        if (_observers.isEmpty()) {
            return;
        }
        if (_stateChanged){
        for (Observer observer : _observers) {
            observer.update();
            }
            _stateChanged = false;
        }
    }
}

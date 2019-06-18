package mum.edu.swe.trailerrentalserver.observe;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}

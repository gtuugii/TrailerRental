package mum.edu.swe.trailerrentalserver.observe;

public class LoggerObserver extends Observer {
    @Override
    public void update() {
        System.out.println("Logging the rent...");

    }

    public LoggerObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

}

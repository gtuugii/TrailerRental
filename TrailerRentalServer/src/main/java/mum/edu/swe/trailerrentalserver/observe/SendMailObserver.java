package mum.edu.swe.trailerrentalserver.observe;

public class SendMailObserver extends Observer {

    @Override
    public void update() {
        System.out.println("Sending mail to Customer/User");
    }

    public SendMailObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

}

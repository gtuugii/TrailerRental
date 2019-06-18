package mum.edu.swe.trailerrentalserver.observe;

public class SaveObserver extends Observer {
    @Override
    public void update() {
        // TODO Auto-generated method stub
        System.out.println("Saving Rent a Trailer to Database");
    }

    public SaveObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

}

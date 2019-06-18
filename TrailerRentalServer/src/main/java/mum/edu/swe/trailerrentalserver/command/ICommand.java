package mum.edu.swe.trailerrentalserver.command;

public interface ICommand {
    void execute();
    void undo();
    void redo();
}

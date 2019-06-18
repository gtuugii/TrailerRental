package mum.edu.swe.trailerrentalserver.service.observer;

import mum.edu.swe.trailerrentalserver.command.ICommand;
import mum.edu.swe.trailerrentalserver.domain.Rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class RentCart {
    //private List<CartItem> cartItem = new ArrayList();
    private Rent item;
    //private Category product;
    private Stack<ICommand> undoStack = new Stack<ICommand>();
    private Stack<ICommand> redoStack = new Stack<ICommand>();

    public RentCart(Rent Item) {
        super();
        this.item = Item;
    }

    public void add() {
        //cartItem.add(item);
        System.out.println("Adding to Rent: " + item.getTrailerId().getNumber() + " " + item.getAmount());
    }

    public void remove() {
        //cartItem.remove(item);
        System.out.println("Removing from Rent: " + item.getTrailerId().getNumber() + " " + item.getAmount());
    }
}

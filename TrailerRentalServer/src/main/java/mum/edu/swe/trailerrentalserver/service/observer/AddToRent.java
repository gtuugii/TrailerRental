package mum.edu.swe.trailerrentalserver.service.observer;

import mum.edu.swe.trailerrentalserver.command.ICommand;

public class AddToRent implements ICommand {
    private RentCart rentCart;

    @Override
    public void execute() {
        rentCart.add();
    }

    @Override
    public void undo() {
        rentCart.remove();
    }

    @Override
    public void redo() {
        rentCart.add();
    }

    public RentCart getRentCart() {
        return rentCart;
    }

    public void setRentCart(RentCart rentCart) {
        this.rentCart = rentCart;
    }

    public AddToRent(RentCart rentCart) {
        super();
        this.rentCart = rentCart;
    }
}

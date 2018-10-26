package company;

import company.entities.Room;

public interface BuildingInterface {
    int number = 0;
    String street = "Null";

    void addRoom(int number, int square, int countOfWindows);

    Room getRoom(int number);

    void describe();

    void deleteRoom(int number);

    void validate();

    boolean isValid();

}

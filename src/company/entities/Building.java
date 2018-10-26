package company.entities;

import company.BuildingInterface;
import company.exceptions.LackOfRoomException;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Building implements BuildingInterface {

    private String street;
    private int number;
    private boolean valid;

    private LinkedHashSet<Room> rooms = new LinkedHashSet<Room>();

    public Building(String street, int number) {
        this.street = street;
        this.number = number;
        this.valid = false;
    }

    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void addRoom(int number, int square, int countOfWindows) {
        rooms.add(new Room(number, square, countOfWindows));
    }

    @Override
    public Room getRoom(int number) {

        for (Room room : rooms
                ) {
            if (room.getNumber() == number) {
                return room;
            }

        }
        //Если добавление комнат идет по порядку, то можно проще
        //return Rooms.get(Number - 1);
        throw new LackOfRoomException("Запрашиваемая вами комната не существует");

    }

    @Override
    public void describe() {
        System.out.println("Здание " + street + " " + number);
        for (Room room : rooms
                ) {
            room.describe();
        }
    }

    @Override
    public void deleteRoom(int number) {
        Iterator<Room> iterator = rooms.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getNumber() == number) {
                iterator.remove();
            }
        }
    }

    @Override
    public void validate() {
//        Если нам не важна информация обо всех нарушениях, то так:
//        for (Room room : rooms
//                ) {
//            if (!room.isValid()) {
//                return;
//            }
//
//        }
//        valid = true;

        //В данном случае мы получим все нарушения
        boolean someRoomIsInvalid = false;
        for (Room room : rooms
                ) {
            if (!room.isValid()) {
                someRoomIsInvalid = true;
            }
        }
        if (!someRoomIsInvalid) {
            valid = true;
        }
    }

    @Override
    public boolean isValid() {
        return valid;
    }
}

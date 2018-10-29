package company.entities;

import company.BuildingInterface;
import company.exceptions.InvalidSquareOfRoomException;
import company.exceptions.LackOfRoomException;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Building implements BuildingInterface {

    private String street;
    private int number;
    private boolean valid;
    /*Переменная для хранения всего множества комнат*/
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
        try {
            for (Room room : rooms
                    ) {
                if (room.getNumber() == number) {
                    return room;
                }

            }
            throw new LackOfRoomException("Запрашиваемая вами комната не существует");
        }
        //Если добавление комнат идет по порядку, то можно проще
        //return Rooms.get(Number - 1);
        catch (LackOfRoomException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void describe() {
        System.out.println("Здание " + street + " " + number);
        for (Room room : rooms
                ) {
            /*Вызываем описание каждой комнаты*/
            room.describe();
        }
    }

    @Override
    public void deleteRoom(int number) {
        /*Проходим по множеству и удаляем, если номер комнаты совпадает с переданным */
        Iterator<Room> iterator = rooms.iterator();
        try {
            while (iterator.hasNext()) {
                if (iterator.next().getNumber() == number) {
                    iterator.remove();
                    return;
                }
            }
            throw new LackOfRoomException("Запрашиваемая вами комната не существует");
        }
        catch(LackOfRoomException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void validate() {
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

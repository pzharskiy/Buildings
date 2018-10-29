package company;


import company.entities.Lightbulb;
import company.entities.furnitures.Furniture;


public interface RoomInterface {
    int number = 0;
    int square = 0;
    int illumination=0;
    int windows = 0;
    double occupiedArea=0;
    boolean valid = false;

    void add(Furniture furniture);

    void add(Lightbulb lightbulb);

    void change(Furniture furniture, Furniture updateFurniture);

    void change(Lightbulb lightbulb, Lightbulb updateLightbulb);

    void delete(Furniture furniture);

    void delete(Lightbulb lightbulb);

    void describe();

    boolean checkOccupancy();

    boolean checkIllumination();

    boolean isValid();

    void validate();


}

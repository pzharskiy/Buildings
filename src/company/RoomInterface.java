package company;


import company.entities.Lightbulb;
import company.entities.furnitures.Furniture;
import company.exceptions.IlluminanceTooMuchException;
import company.exceptions.LackOfFurnitureException;
import company.exceptions.LackOfLightbulbException;
import company.exceptions.SpaceUsageTooMuchException;

public interface RoomInterface {
    int number = 0;
    int square = 0;
    int illumination=0;
    int windows = 0;
    double occupiedArea=0;
    boolean valid = false;

    void add(Furniture furniture);

    void add(Lightbulb lightbulb);

    void change(Furniture furniture, Furniture updateFurniture) throws LackOfFurnitureException;

    void change(Lightbulb lightbulb, Lightbulb updateLightbulb) throws LackOfFurnitureException;

    void delete(Furniture furniture) throws LackOfLightbulbException;

    void delete(Lightbulb lightbulb) throws LackOfLightbulbException;

    void describe();

    boolean checkOccupancy();

    boolean checkIllumination();

    boolean isValid();

    void validate();


}

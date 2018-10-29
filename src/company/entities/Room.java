package company.entities;

import company.RoomInterface;
import company.entities.furnitures.FoldingFurniture;
import company.entities.furnitures.Furniture;
import company.exceptions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Room implements RoomInterface {
    private int number;
    private int square;
    private int illumination;
    private int windows;
    private int occupiedArea;
    private boolean valid;
    /*Переменные для хранения списков мебели и лампочек*/
    private List<Furniture> furnitures = new ArrayList<Furniture>();
    private List<Lightbulb> lightbulbs = new ArrayList<Lightbulb>();

    public Room(int number, int square, int countOfWindows) {
        if (square > 0) {
            this.number = number;
            this.square = square;
            this.windows = countOfWindows;
            this.illumination = countOfWindows * 700;
            this.valid = false;
        } else
            throw new InvalidSquareOfRoomException("Площадь вашей комнаты должна быть положительным числом. Пожалуйста, проверьте корректность введённых данных");
    }

    @Override
    public void add(Furniture furniture) {

        furnitures.add(furniture);
        //Если мебель мягкая, то добавляем ее максимальные размеры, иначе - размеры твердой мебели
        if (furniture instanceof FoldingFurniture) occupiedArea += ((FoldingFurniture) furniture).getMaxSize();
        else occupiedArea += furniture.getSize();
    }

    @Override
    public void add(Lightbulb lightbulb) {
        illumination += lightbulb.getIllumination();
        lightbulbs.add(lightbulb);
    }

    @Override
    public void change(Furniture furniture, Furniture updateFurniture) {
        try {
            if (furnitures.contains(furniture)) {
                furnitures.set(furnitures.indexOf(furniture), updateFurniture);
            } else {
                throw new LackOfFurnitureException("Запрашиваемый объект не найден или не существует");
            }
        } catch (LackOfFurnitureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void change(Lightbulb lightbulb, Lightbulb updateLightbulb) {
        try {
            if (lightbulbs.contains(lightbulb)) {

                lightbulbs.set(lightbulbs.indexOf(lightbulb), updateLightbulb);
            } else {
                throw new LackOfLightbulbException("Запрашиваемый объект не найден или не существует");
            }
        } catch (LackOfFurnitureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Furniture furniture) {
        try {
            if (furnitures.contains(furniture)) {
                furnitures.remove(furniture); //Удаляет дублирущиеся объекты
            } else {
                throw new LackOfFurnitureException("Запрашиваемый объект не найден или не существует");
            }
        } catch (LackOfFurnitureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Lightbulb lightbulb) {
        try {
            if (lightbulbs.contains(lightbulb)) {
                lightbulbs.remove(lightbulb); //Удаляет дублирущиеся объекты
            } else {
                throw new LackOfLightbulbException("Запрашиваемый объект не найден или не существует");
            }
        } catch (LackOfFurnitureException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOccupancy() {

        if (((double) occupiedArea / square) <= 0.7) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean checkIllumination() {

        if (illumination <= 4000 && illumination >= 300) {
            return true;
        } else return false;
    }

    @Override
    public void describe() {
        System.out.println("\tКомата №" + number);
        printLightbulds();
        System.out.print("\t\tПлощадь = " + square + " м^2 ");
        printFurnitures();

    }

    public int getNumber() {
        return number;
    }

    @Override
    public void validate() {
        /*Проверка заполненности и светимости*/
        if ((!checkOccupancy()) || (!checkIllumination())) {
            /*Если нарушена хотя бы одно, то проверяется что именно и выбрасывается соответсвенное исключение*/
            valid = false;
            try {
                if (!checkOccupancy()) {
                    throw new SpaceUsageTooMuchException("Превышена допустимая заполненность помещения (более 70% площади) в комнате №" + number);
                }
            } catch (SpaceUsageTooMuchException e) {
                e.printStackTrace();
            }

            try {
                if (illumination < 300) {

                    throw new IlluminanceTooLittleException("Светимость ниже допустимой в помещении (менее 300 лк) в комнате №" + number);
                }
                if (illumination > 4000) {
                    throw new IlluminanceTooMuchException("Превышена допустимая светимость в помещении (более 4000 лк) в комнате №" + number);
                }
            } catch (IlluminanceTooMuchException | IlluminanceTooLittleException e) {
                e.printStackTrace();
            }

        } else {
            valid = true;
        }
    }

    @Override
    public boolean isValid() {

        validate();
        return valid;
    }

    private void printFurnitures() {
        /*Если мебели нет*/
        if (furnitures.isEmpty()) {
            System.out.println("(свободно 100%)");
            System.out.println("\t\tМебели нет");
        } else {
            System.out.print("(занято ");
            printOccupancy();
            System.out.println("\t\tМебель: ");
            for (Furniture furniture : furnitures
                    ) {
                furniture.print();
            }
        }
    }

    private void printOccupancy() {
        int minSquare = 0;
        int maxSquare = 0;
        for (Furniture furniture : furnitures
                ) {

            minSquare += furniture.getSize();
            /*Если мебель - мягкая*/
            if (furniture instanceof FoldingFurniture) {
                maxSquare += ((FoldingFurniture) furniture).getMaxSize();
            }
            /*Иначе*/
            else {
                maxSquare += furniture.getSize();
            }

        }
        /*Если среди мебели присутствует мягкая, то */
        if (maxSquare > minSquare) {
            System.out.println(minSquare + "-" + maxSquare + " м^2, гарантировано свободно " + (square - maxSquare) + " м^2 или " + (100 * (square - maxSquare) / square) + "% площади)");
        } else
            System.out.println(minSquare + " м^2, свободно " + (square - minSquare) + " м^2 или " + (100 * (square - minSquare) / square) + "% площади)");

    }

    private void printLightbulds() {

        System.out.print("\t\tОсвещенность = " + illumination + " (" + windows + " окна по 700лк");
        if (lightbulbs.isEmpty()) {
            System.out.println(")");
        } else {
            System.out.print(", лампочки:");
            for (Lightbulb lightbulb : lightbulbs
                    ) {
                lightbulb.print();
            }
            System.out.print("\b");
            System.out.println(")");
        }
    }
}

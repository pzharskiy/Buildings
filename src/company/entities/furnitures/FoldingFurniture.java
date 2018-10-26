package company.entities.furnitures;

public abstract class FoldingFurniture extends Furniture {
    private int maxSize;

    public FoldingFurniture(String name, int size, int maxSize) {
        super(name, size);
        this.maxSize = maxSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    @Override
    public void print() {
        System.out.println("\t\t  " + getName() + " (площадь от " + getSize() + " м^2 до " + getMaxSize() + " м^2)");
    }
}

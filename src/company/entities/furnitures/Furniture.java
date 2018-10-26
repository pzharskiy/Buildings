package company.entities.furnitures;

import company.Printable;

public abstract class Furniture implements Printable{
    private int size;
    private String name;

    public Furniture(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println("\t\t  " + name + " (площадь " + size + " м^2)");
    }

    @Override
    public boolean equals(Object object)
    {
        if(object == this)
            return true;

     /* furniture ссылается на null */

        if(object == null)
            return false;

     /* Удостоверимся, что ссылки имеют тот же самый тип */

        if(!(getClass() == object.getClass()))
            return false;
        else
        {
            Furniture tmp = (Furniture) object;
            if((tmp.name == this.name) && (tmp.size == this.size))
                return true;
            else
                return false;
        }
    }
}

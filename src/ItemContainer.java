import java.util.ArrayList;

public abstract class ItemContainer {
    public ArrayList<Item> items;

    public ItemContainer() {
        this.items = new ArrayList<>();
    }

    public boolean addItem(Item item) {
        if (item == null) return false;
        items.add(item);
        return true;
    }

    public boolean addItems(ArrayList<Item> items) {
        if (items == null) return false;
        this.items.addAll(items);
        return true;
    }

    public Item removeItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        return (removeItem(name) != null);
    }

    public void displayItemInventory() {
        System.out.println(items.toString());
    }

    public boolean containsItem(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public String getItemInventory() {
        String itemNames = "";
        for (Item item : items) {
            itemNames += item.toString() + ", ";
        }
        return itemNames;
    }

    public String toString() {
        String str = "";
        if (items.size() == 0) return "(none)";
        for (Item item : items) {
            str += item.toString() + ", ";
        }
        return str;
    }
}

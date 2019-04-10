import java.util.ArrayList;

public abstract class ItemContainer {
    public ArrayList<Item> items;

    public ItemContainer() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItems(ArrayList<Item> items) {
        this.items.addAll(items);
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
            itemNames += item.toString() + "; ";
        }
        return itemNames;
    }

    public String toString() {
        return items.toString();
    }
}

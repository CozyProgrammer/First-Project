Author: Hassan Javed

package TextBasedAdventureGame;

public class Item {
    private String itemName;
    private String itemDescription;
    private int itemAffect;

    public Item(String itemName,String itemDescription,int itemAffect) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemAffect = itemAffect;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemAffect() {
        return itemAffect;
    }
}

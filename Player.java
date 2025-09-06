Author: Hassan Javed

package TextBasedAdventureGame;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String playerName;
    private String playerType;
    private String playerWeapon;
    private int playerHealth;
    private List <Item> inventory=new ArrayList<>();

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
        if (playerType.equalsIgnoreCase("Assassin")) {
            playerHealth = 90;
            playerWeapon = "Rusty Dagger";
            Item rustyDagger = new Item("Rusty Dagger", "A small, worn-out dagger with a chipped blade. It’s not pretty, but it’s sharp enough to pierce through weak foes. Better than bare hands",10);
            inventory.add(rustyDagger);
        } else if (playerType.equalsIgnoreCase("Archer")) {
            playerHealth = 90;
            playerWeapon = "Short Bow";
            Item shortArcher = new Item("Short  Bow", "A lightweight wooden bow with a frayed string. Its range is limited, but it allows you to strike before enemies get too close",8);
            inventory.add(shortArcher);
        } else if (playerType.equalsIgnoreCase("Mage")) {
            playerHealth = 70;
            playerWeapon = "Apprentice Staff";
            Item apprenticeStaff = new Item("Apprentice Staff", "A wooden staff engraved with faint runes. It channels small bursts of magical energy, hinting at greater power yet to be unlocked",12);
            inventory.add(apprenticeStaff);
        } else if (playerType.equalsIgnoreCase("Healer")) {
            playerHealth = 100;
            playerWeapon = "Wooden Rod";
            Item woodenRod = new Item("Wooden Rod", "A plain wooden rod often used for rituals. It isn’t strong as a weapon, but it provides balance between striking and supporting allies",6);
            inventory.add(woodenRod);
        }
    }

    public Player(String playerName) {
        this.inventory = new ArrayList<>();
        this.playerName = playerName;
        }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public int getPlayerHealth() {
        return playerHealth;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public String getPlayerWeapon() {
        return playerWeapon;
    }
}

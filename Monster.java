package TextBasedAdventureGame;

public class Monster {
    private String monsterName;
    private String monsterDescription;
    private int monsterHealth;
    private int monsterAttack;

    public Monster(String monsterName, String monsterDescription ) {
        this.monsterDescription = monsterDescription;
        this.monsterName = monsterName;
    }

    public void setMonsterAttack(int monsterAttack) {
        this.monsterAttack = monsterAttack;
    }

    public void setMonsterHealth(int monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public int getMonsterAttack() {
        return monsterAttack;
    }

    public int getMonsterHealth() {
        return monsterHealth;
    }

    public String getMonsterDescription() {
        return monsterDescription;
    }

    public String getMonsterName() {
        return monsterName;
    }
}

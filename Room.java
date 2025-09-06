Author: Hassan Javed

package TextBasedAdventureGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room {
    private String roomName;
    private String roomDescription;
    private List<Monster> monsterList;

    public Room( String roomName, String roomDescription) {
        this.monsterList=new ArrayList<>();
        this.roomDescription = roomDescription;
        this.roomName = roomName;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public String getRoomName() {
        return roomName;
    }

}

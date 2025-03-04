package dataaccess;

import java.util.ArrayList;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO{
    final private HashMap<Integer, ArrayList<String>> Games = new HashMap<>();
    @Override
    public void clearAllGames() {
        Games.clear();
    }
}

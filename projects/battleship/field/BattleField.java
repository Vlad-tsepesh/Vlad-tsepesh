package projects.battleship.field;

import java.util.LinkedHashMap;
import java.util.Map;

public class BattleField {
    public String name;
    public Map<String, String> battleField = new LinkedHashMap<>();

    public BattleField(String name) {
        this.name = name;
        for (char c = '@'; c <= 'J'; c++) {
            for (int i = 0; i <= 11; i++) {
                String key = "" + c + i;
                String value = (i == 0 && c != '@') ? "" + c : (i == 11) ? "\n" : c != '@' ? " ~" : (i == 0) ? " " : " " + i;
                battleField.put(key, value);
            }
        }
    }

    public void showField() {
        for (String value : battleField.values()) {
            System.out.print(value);
        }
    }

    public void fogOfWar() {
        for (String value : battleField.values()) {
            if (value.equals(" O"))
                System.out.print(" ~");
            else
                System.out.print(value);
        }
    }

    public void setShipOnField(String[] keys) {
        for (String key : keys) {
            battleField.put(key, " O");
        }
    }
}
package net.dazay.util;

import javafx.util.Pair;
import net.dazay.model.Animal;

import java.util.List;

public class Util
{
    /**
     *
     * @param pairList - лист пар имя/кол-во посещений
     * @return единая строка для отображения всех пар
     */
    public static String pairToString(List<Pair<String, Integer>> pairList)
    {
        String result = "";
        for(Pair<String, Integer> pair : pairList)
        {
            result = result.concat("Animal: " + pair.getKey() + "\nVisits: " + pair.getValue() + "\n\n");
        }
        return result;
    }
}

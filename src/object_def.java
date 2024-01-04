import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class object_def {
    private List<SimpleEntry<Integer, Integer>> pairList;
    private String NameString;
    public object_def(String NameString, List<SimpleEntry<Integer, Integer>> pairList) {
        this.pairList = pairList;
        this.NameString = NameString;
    }

    public List<SimpleEntry<Integer, Integer>> getPairList() {
        return pairList;
    }

    public void setPairList(List<SimpleEntry<Integer, Integer>> pairList) {
        this.pairList = pairList;
    }
    public String getNameString() {
        return NameString;
    }

    public void setNameString(String nameString) {
        this.NameString = NameString;
    }

    @Override
    public String toString() {
        return NameString + pairList ;
    }
}

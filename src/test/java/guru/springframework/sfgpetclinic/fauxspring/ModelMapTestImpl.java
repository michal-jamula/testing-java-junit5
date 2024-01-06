package guru.springframework.sfgpetclinic.fauxspring;

import java.util.HashMap;
import java.util.Map;

public class ModelMapTestImpl implements Model{

    private Map<String, Object> map;


    public ModelMapTestImpl() {
        map = new HashMap<>();
    }

    @Override
    public void addAttribute(String key, Object o) {
        map.put(key, o);
    }

    @Override
    public void addAttribute(Object o) {
        //Not used
    }

    public Map<String, Object> getMap() {
        return map;
    }

}

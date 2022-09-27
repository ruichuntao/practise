package rui.bean;

import java.util.List;

public class Province {

    public String name;
    public List<City> subOpt;

    public static class City {
        public String name;
    }

}

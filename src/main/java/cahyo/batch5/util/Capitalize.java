package cahyo.batch5.util;

/*
* https://stackoverflow.com/questions/25223553/how-can-i-create-an-utility-class
* */
public final class Capitalize {
    public static String capitalize(String param) {
        return param.substring(0, 1).toUpperCase() + param.substring(1);
    }
}

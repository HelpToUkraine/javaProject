package Course_work;

import MyLibrary.LinkedList;
import MyLibrary.Stack;
import MyLibrary.QueueList;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        BinaryTree shopMap = new BinaryTree();
        BinaryTree clientMap = new BinaryTree();

        shopMap.put("Lacoste", "Перемоги '7' просп.");
        shopMap.put("KFC", "Чернігівська '16' вул.");
        shopMap.put("Okko", "Хрещатик '12' вул.");
        shopMap.put("Eko", "Борщагівська '38' вул.");
        shopMap.put("Metro", "Софіївська '13' вул.");
        shopMap.put("Fora", "Академіка Янгеля '5' вул.");
        shopMap.put("Apple", "Героїв України '2' вул.");
        shopMap.put("Varus", "Тараса Шевченка '48' бульв.");
        shopMap.put("ATB", "Лесі Українки '37' бульв.");
        shopMap.put("McDonald's", "Оболонська '3' вул.");
        shopMap.put("Eva", "Райдужна '7' вул.");
        shopMap.put("Comfy", "Конотопська '48' вул.");
        shopMap.put("Novus", "Василя Стуса '21' вул.");
        shopMap.put("Rozetka", "Богдана Хмельницького '4' вул.");

        shopMap.print();



        System.out.println(shopMap.getValue("Atb"));
        System.out.println(shopMap.getValueRecursive("ATB"));
    }
}

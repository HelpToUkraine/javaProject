package Course_work;

import MyLibrary.QueueList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();                              // Tree: <Shop, ShopAddress>
        BinaryTree clientMap = new BinaryTree();                            // Tree: <Client, ClientAddress>
        QueueList deliveryQueue = new QueueList();                          // Черга клієнтів
        InputData inputData = new InputData();                              // Зчитування даних з файлів

        /* create Graph after read inputData in Files */
        Graph graph = inputData.readFiles(shopMap, clientMap, deliveryQueue);

        /* заповнення списка вершин Graph з Binary Tree (clientMap, shopMap) */
        clientMap.traverseInOrder(graph);
        shopMap.traverseInOrder(graph);

        /* The main cycle of the program. */
        start(graph, shopMap, clientMap, deliveryQueue);
    }

    public static void start(Graph graph, BinaryTree shopMap, BinaryTree clientMap, QueueList deliveryQueue) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print(Blue + """   
                                        
                    Доставка наступного клієнта:    <1>
                    Черга доставки:                 <2>
                    Вершини графа:                  <3>
                    Таблиця Shops:                  <4>
                    Таблиця Clients:                <5>
                    Матриця суміжності:             <6>
                    Закрити програму:            <Another>
                    """ + Reset);

            String var = input.nextLine();
            switch (var) {
                case "1" -> {
                    /* After successfully delivery -> remove Client from deliveryQueue */
                    System.out.println("\n" + Yellow + "Обробка наступного замовлення: Client -> Shop" + Reset);
                    nextDelivery(graph, shopMap, clientMap, deliveryQueue);
                }
                case "2" -> {
                    System.out.println("\n" + Yellow + "Черга клієнтів на доставку:" + Reset);
                    deliveryQueue.print();
                }
                case "3" -> {
                    System.out.println("\n" + Yellow + "Вершини Graph:" + Reset);
                    graph.printVertex();
                }
                case "4" -> {
                    System.out.println("\n" + Yellow + "BinaryTree<Shops> sorted by 'Key'" + Reset);
                    shopMap.print();
                }
                case "5" -> {
                    System.out.println("\n" + Yellow + "BinaryTree<Clients> sorted by 'Key'" + Reset);
                    clientMap.print();
                }
                case "6" -> {
                    System.out.println("\n" + Yellow + "Матриця суміжності Graph:" + Reset);
                    graph.printMatrix();
                }
                default -> {
                    System.err.println("The program completed successfully");
                    System.exit(0);
                }
            }
        }
    }

    public static void nextDelivery(Graph graph, BinaryTree shopMap, BinaryTree clientMap, QueueList deliveryQueue) {
        if (deliveryQueue.getHead() != null) {                                   // поки є клієнти в черзі замовлень
            QueueList.Node order = deliveryQueue.remove();                       // дістається замовлення і видаляється
            String clientAddress = clientMap.getValue((String) order.key);
            String shopAddress = shopMap.getValue((String) order.value);
            Scanner input = new Scanner(System.in);

            if (clientAddress != null && shopAddress != null) {                 // якщо в дереві є Client і Shop
                System.out.printf("Client:\t%5s\t\tAddress: %s\nShop:\t%5s\t\tAddress: %s\n",
                        order.key, clientAddress, order.value, shopAddress);
                algoritmGraph(graph, clientAddress, shopAddress);

                System.out.print(Blue + "Розірвати вершину і перерахувати шлях <1> | <Another>: " + Reset);
                String var = input.nextLine();
                if (var.equals("1")) {
                    System.out.print("\n" + Yellow + "Введіть номер(и) вершини Graph для видалення(id): " + Reset);
                    String[] delVertex = input.nextLine().split(" ");
                    for (String vertex : delVertex) {
                        try {
                            System.out.printf("%b\t<-\tDeleted vertexId: %s\n", graph.removeVertex(Integer.parseInt(vertex)), vertex);
                        } catch (Exception ignored) {
                            System.out.printf("false\t<-\tDeleted vertexId: %s\n", vertex);
                        }
                    }

                    System.out.print(Blue + "\nПерераховані шляхи:" + Reset);
                    algoritmGraph(graph, clientAddress, shopAddress);
                }
            } else System.out.println("Client or Shop not in BinaryTree");

        } else System.out.println("All orders are delivered");
    }

    public static void algoritmGraph(Graph graph, String clientAddress, String shopAddress) {
        System.out.println("\n" + Yellow + "DFS(пошук у глибину): Client -> Shop" + Reset);
        graph.dfs(clientAddress, shopAddress);

        System.out.println("\n" + Yellow + "BFS(пошук у ширину): Client -> Shop" + Reset);
        graph.bfs(clientAddress, shopAddress);

        System.out.println("\n" + Yellow + "Dijkstra:(мінімальний час) Shop -> Client" + Reset);
        graph.dijkstra(clientAddress, shopAddress);
    }

    static final String Blue = "\u001B[96m";
    static final String Yellow = "\u001B[93m";
    static final String Reset = "\u001B[0m";
}
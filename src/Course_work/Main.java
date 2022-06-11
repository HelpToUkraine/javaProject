package Course_work;

import MyLibrary.QueueList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree shopMap = new BinaryTree();                              // Tree: <Shop, ShopAddress>
        BinaryTree clientMap = new BinaryTree();                            // Tree: <Client, ClientAddress>
        QueueList deliveryQueue = new QueueList();                          // Черга клієнтів
        InputData inputData = new InputData();                              // зчитування даних з файлів

        /* create graph after read inputData in Files */
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
            System.out.print("""   
                             
                    Обробити доставку наступного клієнта: <1>
                    Черга замовлень: <2>
                    Вершини графа: <3>
                    Таблиця магазинів <4>
                    Таблиця клієнтів <5>
                    Матриця суміжності: <6>
                    Розірвати вершину графа: <7>
                    Закрити програму: <Another Key>
                    """);
            String var = input.nextLine();
            switch (var) {
                case "1" -> {
                    /* After successfully delivery -> remove Client from deliveryQueue */
                    System.out.println("\nОбробка наступного замовлення:");
                    nextDelivery(graph, shopMap, clientMap, deliveryQueue);
                }
                case "2" -> {
                    System.out.println("\nЧерга клієнтів на доставку:");
                    deliveryQueue.print();
                }
                case "3" -> {
                    System.out.println("\nВершини Graph:");
                    graph.vertexList.print();
                }
                case "4" -> {
                    System.out.println("\nBinaryTree<Shops> sorted by 'Key'");
                    shopMap.print();
                }
                case "5" -> {
                    System.out.println("\nBinaryTree<Clients> sorted by 'Key'");
                    clientMap.print();
                }
                case "6" -> {
                    System.out.println("\nМатриця суміжності Graph:");
                    graph.printMatrix();
                }
                case "7" -> {
                    System.out.print("Введіть номер(id) вершини для видалення: ");
                    String delVertex = input.nextLine();
                    graph.removeVertex(Integer.parseInt(delVertex));
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
            QueueList.Node order = deliveryQueue.remove();                       // дістається клієнт і видаляється
            String clientAddress = clientMap.getValue((String) order.key);
            String shopAddress = shopMap.getValue((String) order.value);

            System.out.printf("Client: '%s'\t\tAddress: %s\nShop: '%s'\t\tAddress: %s\n",
                    order.key, clientAddress, order.value, shopAddress);

            System.out.println("\nDFS(пошук у глибину):");
            graph.dfs(clientAddress, shopAddress);

            System.out.println("\nBFS(пошук у ширину):");
            graph.bfs(clientAddress, shopAddress);

            System.out.println("\nDijkstra:");
            graph.dijkstra(clientAddress, shopAddress);

            /*
            Спросить хотите ли разорвать вершину и пересчитать пути?
            если да -> разорвать и перезапустить.
             */

        } else
            System.out.println("All orders are delivered");
    }

//    public static BinaryTree getShopMap(){
//        return shopMap;
//    }
}

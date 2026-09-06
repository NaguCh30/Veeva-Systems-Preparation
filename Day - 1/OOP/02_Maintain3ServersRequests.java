/*
 * Server Request Load Balancer
 *
 * There are 3 servers in a system. Each server currently has a certain
 * number of active connections.
 *
 * Implement a load balancer that allocates every new incoming request
 * to the server having the minimum number of active connections.
 *
 * Requirements:
 *
 * 1. Take the initial number of connections for:
 *      - Server 1
 *      - Server 2
 *      - Server 3
 *
 * 2. Whenever a new request arrives:
 *      - Find the server with the minimum number of connections.
 *      - Allocate the request to that server.
 *      - Increase its connection count by 1.
 *
 * 3. After every allocation, display:
 *      - Which server received the request.
 *      - Current connection count of all three servers.
 *
 * 4. If two or more servers have the same minimum number of connections,
 *    allocate the request to the server with the smallest server number.
 *
 * 5. Provide a menu:
 *      1. Enter a new request
 *      2. Exit
 *
 *
 * Example:
 *
 * Initial Connections:
 * Server 1: 5
 * Server 2: 3
 * Server 3: 4
 *
 * New Request:
 * Server 2 gets the request.
 *
 * Servers Status:
 * Server 1: 5
 * Server 2: 4
 * Server 3: 4
 *
 * New Request:
 * Server 2 gets the request again because Server 2 and Server 3
 * have the same minimum load, and Server 2 has the smaller number.
 *
 * Servers Status:
 * Server 1: 5
 * Server 2: 5
 * Server 3: 4
 *
 * New Request:
 * Server 3 gets the request.
 */




package OOP;

import java.util.Scanner;

class ServerRequests {
    
    static int[] servers = new int[3];

    static void allocateServer() {

        int server = 0;

        if (servers[1] < servers[server]) {
            server = 1;
        }

        if (servers[2] < servers[server]) {
            server = 2;
        }

        servers[server]++;

        System.out.println("Request Allocated to Server  " + (server + 1));
        System.out.println("Servers Status");
        System.out.println("Server 1 : " + servers[0] + " | Server 2 : " + servers[1] + " | Server 3 : " + servers[2]);
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("Enter Initial Connections Each Server Having Now");
        System.out.print("Server 1: ");
        int server1 = sc.nextInt();
        System.out.print("Server 2: ");
        int server2 = sc.nextInt();
        System.out.print("Server 3: ");
        int server3 = sc.nextInt();
        sc.nextLine();

        servers[0] = server1;
        servers[1] = server2;
        servers[2] = server3;

        while (true) {
            System.out.println();
            System.out.println("========== MENU ==========");
            System.out.println("1. Enter a new request");
            System.out.println("2. Exit");

            System.out.print("Enter your choice: ");
            String choice = sc.next();

            if (choice.toLowerCase().equals("2")) {
                System.out.println("Exiting from the program..!");
                break;
            }

            if (choice .toLowerCase().equals("1")) {
                System.out.println("Allocating your request to a server. Please Wait..!");
                allocateServer();
            } else {
                System.out.println("Please enter a valid option");
            }
        }
    }
}

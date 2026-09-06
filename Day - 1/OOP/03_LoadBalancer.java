/*
 * Dynamic Server Request Load Balancer
 *
 * There are multiple servers in a system. Each server can handle multiple
 * active connections. Every request has a start time and an end time.
 *
 * Implement a Server Manager that distributes incoming requests among
 * servers based on their current load.
 *
 * Requirements:
 *
 * 1. Take the number of servers as input.
 *
 * 2. For each server, maintain its active connections using a
 *    PriorityQueue.
 *
 * 3. Each Connection should contain:
 *      - Start Time
 *      - End Time
 *
 * 4. The PriorityQueue of each server should be ordered by End Time,
 *    with the connection having the earliest end time at the top.
 *
 * 5. Whenever a new request arrives:
 *      - First remove all expired connections from every server.
 *      - A connection is considered expired when:
 *            endTime <= currentTime
 *      - Find the server having the minimum number of active connections.
 *      - Allocate the new request to that server.
 *
 * 6. If multiple servers have the same minimum number of connections,
 *    allocate the request to the server with the smallest server number.
 *
 * 7. After allocating a request, display:
 *      - Server to which the request was allocated.
 *      - Number of active connections on each server.
 *      - Details of active connections.
 *
 * 8. Provide a menu:
 *      1. Add Request
 *      2. Display Server Status
 *      3. Exit
 *
 * 9. While adding a request:
 *      - Start Time must be less than End Time.
 *      - Display an error message if End Time <= Start Time.
 *
 *
 * Example:
 *
 * Number of Servers: 3
 *
 * Request 1:
 * Start Time = 1
 * End Time   = 10
 *
 * Request 2:
 * Start Time = 2
 * End Time   = 8
 *
 * Request 3:
 * Start Time = 3
 * End Time   = 6
 *
 * Request 4:
 * Start Time = 7
 * End Time   = 15
 *
 *
 * Before allocating Request 4 at time 7:
 *
 * Server 1 -> [1 - 10]
 * Server 2 -> [2 - 8]
 * Server 3 -> [3 - 6]
 *
 * Connection [3 - 6] has expired because:
 *
 *     6 <= 7
 *
 * Therefore, it is removed from Server 3.
 *
 * Active connections:
 *
 * Server 1 -> 1
 * Server 2 -> 1
 * Server 3 -> 0
 *
 * Request 4 is therefore allocated to Server 3.
 *
 *
 * The system should always allocate a new request to the
 * least-loaded server after removing expired connections.
 */




package OOP;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;


class Connection {

    int startTime;
    int endTime;

    Connection(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[" + startTime + " - " + endTime + "]";
    }
}


class ServerManager {

    List<PriorityQueue<Connection>> servers;


    ServerManager(int numberOfServers) {

        servers = new ArrayList<>();

        for (int i = 0; i < numberOfServers; i++) {

            PriorityQueue<Connection> queue =
                    new PriorityQueue<>(
                            (a, b) -> Integer.compare(
                                    a.endTime,
                                    b.endTime
                            )
                    );

            servers.add(queue);
        }
    }


    void removeExpiredConnections(int currentTime) {

        for (PriorityQueue<Connection> queue : servers) {

            while (!queue.isEmpty()
                    && queue.peek().endTime <= currentTime) {

                queue.poll();
            }
        }
    }

    int findLeastLoadedServer() {

        int server = 0;

        for (int i = 1; i < servers.size(); i++) {

            if (servers.get(i).size()
                    < servers.get(server).size()) {

                server = i;
            }
        }

        return server;
    }


    void addRequest(int startTime, int endTime) {

        removeExpiredConnections(startTime);

        int server = findLeastLoadedServer();

        Connection connection =
                new Connection(startTime, endTime);

        servers.get(server).add(connection);

        System.out.println();
        System.out.println(
                "Request allocated to Server "
                        + (server + 1)
        );

        displayStatus();
    }


    void displayStatus() {

        System.out.println();
        System.out.println("========== SERVER STATUS ==========");

        for (int i = 0; i < servers.size(); i++) {

            PriorityQueue<Connection> queue =
                    servers.get(i);

            System.out.println(
                    "Server " + (i + 1)
                    + " -> Active Connections: "
                    + queue.size()
            );

            PriorityQueue<Connection> temp =
                    new PriorityQueue<>(queue);

            while (!temp.isEmpty()) {

                Connection connection = temp.poll();

                System.out.println(
                        "    " + connection
                );
            }
        }

        System.out.println("===================================");
    }
}


class ServerRequests {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of servers: ");
        int n = sc.nextInt();

        ServerManager manager =
                new ServerManager(n);


        while (true) {

            System.out.println();
            System.out.println("========== MENU ==========");
            System.out.println("1. Add Request");
            System.out.println("2. Display Server Status");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();


            if (choice == 1) {

                System.out.println();
                System.out.println("Enter Request Details");

                System.out.print("Start Time: ");
                int startTime = sc.nextInt();

                System.out.print("End Time: ");
                int endTime = sc.nextInt();


                if (endTime <= startTime) {

                    System.out.println(
                            "End time must be greater than start time."
                    );

                    continue;
                }


                manager.addRequest(
                        startTime,
                        endTime
                );
            }


            else if (choice == 2) {

                System.out.print(
                        "Enter current time: "
                );

                int currentTime = sc.nextInt();

                manager.removeExpiredConnections(
                        currentTime
                );

                manager.displayStatus();
            }


            else if (choice == 3) {

                System.out.println(
                        "Exiting from the program..!"
                );

                break;
            }


            else {

                System.out.println(
                        "Please enter a valid option!"
                );
            }
        }

        sc.close();
    }
}

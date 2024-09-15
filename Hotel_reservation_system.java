import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Hotel_reservation_system {

        public static void main(String[] args) {
            Hotel hotel = new Hotel();
            hotel.initializeRooms();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n--- Hotel Reservation System ---");
                System.out.println("1. Search for Available Rooms");
                System.out.println("2. Make a Reservation");
                System.out.println("3. View Booking Details");
                System.out.println("4. Exit");

                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (option) {
                    case 1:
                        hotel.searchAvailableRooms();
                        break;
                    case 2:
                        System.out.print("Enter room type (Single/Double/Suite): ");
                        String roomType = scanner.nextLine();
                        System.out.print("Enter number of nights: ");
                        int nights = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        hotel.makeReservation(roomType, nights);
                        break;
                    case 3:
                        hotel.viewBookingDetails();
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }
    }

    class Hotel {
        private Map<String, Room> rooms;
        private List<Reservation> reservations;
        private static int reservationIdCounter = 1;

        public Hotel() {
            rooms = new HashMap<>();
            reservations = new ArrayList<>();
        }

        public void initializeRooms() {
            rooms.put("Single", new Room("Single", 100.00, 10));
            rooms.put("Double", new Room("Double", 150.00, 5));
            rooms.put("Suite", new Room("Suite", 300.00, 3));
        }

        public void searchAvailableRooms() {
            System.out.println("\n--- Available Rooms ---");
            for (Room room : rooms.values()) {
                if (room.getAvailable() > 0) {
                    System.out.printf("Type: %s, Price per night: $%.2f, Available: %d%n",
                            room.getType(), room.getPrice(), room.getAvailable());
                }
            }
        }

        public void makeReservation(String roomType, int nights) {
            Room room = rooms.get(roomType);
            if (room == null || room.getAvailable() <= 0) {
                System.out.println("Sorry, no available rooms of this type.");
                return;
            }

            double totalAmount = room.getPrice() * nights;
            Reservation reservation = new Reservation(reservationIdCounter++, room, nights, totalAmount);
            reservations.add(reservation);
            room.decrementAvailable();
            System.out.printf("Reservation made! Your reservation ID is %d. Total amount: $%.2f%n",
                    reservation.getId(), totalAmount);
        }

        public void viewBookingDetails() {
            System.out.println("\n--- Booking Details ---");
            for (Reservation reservation : reservations) {
                System.out.printf("ID: %d, Room Type: %s, Nights: %d, Total Amount: $%.2f%n",
                        reservation.getId(), reservation.getRoom().getType(),
                        reservation.getNights(), reservation.getTotalAmount());
            }
        }
    }

    class Room {
        private String type;
        private double price;
        private int available;

        public Room(String type, double price, int available) {
            this.type = type;
            this.price = price;
            this.available = available;
        }

        public String getType() {
            return type;
        }

        public double getPrice() {
            return price;
        }

        public int getAvailable() {
            return available;
        }

        public void decrementAvailable() {
            if (available > 0) {
                available--;
            }
        }
    }

    class Reservation {
        private int id;
        private Room room;
        private int nights;
        private double totalAmount;

        public Reservation(int id, Room room, int nights, double totalAmount) {
            this.id = id;
            this.room = room;
            this.nights = nights;
            this.totalAmount = totalAmount;
        }

        public int getId() {
            return id;
        }

        public Room getRoom() {
            return room;
        }

        public int getNights() {
            return nights;
        }

        public double getTotalAmount() {
            return totalAmount;
        }
    }



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class Stock_Trading_platform {

        public static void main(String[] args) {
            // Initialize stock market and user portfolio
            StockMarket market = new StockMarket();
            Portfolio portfolio = new Portfolio();
            Scanner scanner = new Scanner(System.in);

            // Seed some initial stock data
            market.addStock("AAPL", 150.00);
            market.addStock("GOOGL", 2800.00);
            market.addStock("AMZN", 3400.00);

            // Main menu loop
            while (true) {
                System.out.println("\n--- Stock Trading Platform ---");
                System.out.println("1. View Market Data");
                System.out.println("2. Buy Stock");
                System.out.println("3. Sell Stock");
                System.out.println("4. View Portfolio");
                System.out.println("5. Exit");

                System.out.print("Choose an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (option) {
                    case 1:
                        market.displayMarketData();
                        break;
                    case 2:
                        System.out.print("Enter stock symbol to buy: ");
                        String buySymbol = scanner.nextLine().toUpperCase();
                        System.out.print("Enter amount to buy: ");
                        int buyAmount = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        portfolio.buyStock(market, buySymbol, buyAmount);
                        break;
                    case 3:
                        System.out.print("Enter stock symbol to sell: ");
                        String sellSymbol = scanner.nextLine().toUpperCase();
                        System.out.print("Enter amount to sell: ");
                        int sellAmount = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
                        portfolio.sellStock(market, sellSymbol, sellAmount);
                        break;
                    case 4:
                        portfolio.displayPortfolio();
                        break;
                    case 5:
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

    class StockMarket {
        private Map<String, Double> stockPrices;

        public StockMarket() {
            stockPrices = new HashMap<>();
        }

        public void addStock(String symbol, double price) {
            stockPrices.put(symbol, price);
        }

        public double getPrice(String symbol) {
            return stockPrices.getOrDefault(symbol, 0.0);
        }

        public void displayMarketData() {
            System.out.println("\n--- Market Data ---");
            for (Map.Entry<String, Double> entry : stockPrices.entrySet()) {
                System.out.printf("Symbol: %s, Price: $%.2f%n", entry.getKey(), entry.getValue());
            }
        }
    }

    class Portfolio {
        private Map<String, Integer> stocks;
        private double cash;

        public Portfolio() {
            stocks = new HashMap<>();
            cash = 10000.00;  // Initial cash balance
        }

        public void buyStock(StockMarket market, String symbol, int amount) {
            double price = market.getPrice(symbol);
            if (price == 0) {
                System.out.println("Stock symbol not found.");
                return;
            }
            double totalCost = price * amount;
            if (totalCost > cash) {
                System.out.println("Not enough cash to complete the purchase.");
                return;
            }
            stocks.put(symbol, stocks.getOrDefault(symbol, 0) + amount);
            cash -= totalCost;
            System.out.printf("Bought %d shares of %s for $%.2f each. Remaining cash: $%.2f%n", amount, symbol, price, cash);
        }

        public void sellStock(StockMarket market, String symbol, int amount) {
            double price = market.getPrice(symbol);
            if (price == 0) {
                System.out.println("Stock symbol not found.");
                return;
            }
            int ownedAmount = stocks.getOrDefault(symbol, 0);
            if (amount > ownedAmount) {
                System.out.println("Not enough shares to sell.");
                return;
            }
            stocks.put(symbol, ownedAmount - amount);
            double totalRevenue = price * amount;
            cash += totalRevenue;
            System.out.printf("Sold %d shares of %s for $%.2f each. Total revenue: $%.2f, New cash balance: $%.2f%n", amount, symbol, price, totalRevenue, cash);
        }

        public void displayPortfolio() {
            System.out.println("\n--- Portfolio ---");
            System.out.printf("Cash balance: $%.2f%n", cash);
            if (stocks.isEmpty()) {
                System.out.println("No stocks in portfolio.");
            } else {
                for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
                    System.out.printf("Symbol: %s, Shares: %d%n", entry.getKey(), entry.getValue());
                }
            }
        }
    }



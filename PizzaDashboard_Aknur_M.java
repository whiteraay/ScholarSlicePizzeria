import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.*; 
public class PizzaDashboard_Aknur_M extends Application{
	private Scanner console;
    private PizzaInterface pizzaService;
    public static void main(String[] args) {
        launch(args); 
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pizza Dashboard");
        Text welcomeText = new Text("WELCOME TO THE PIZZA APPLICATION !");
        welcomeText.setFont(Font.font("Arial", 25));

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            startPizzaApplication();
        });

        StackPane layout = new StackPane();
        layout.getChildren().addAll(welcomeText, startButton);
        StackPane.setAlignment(welcomeText, javafx.geometry.Pos.TOP_CENTER);

        Scene scene = new Scene(layout, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void startPizzaApplication() {
        console = new Scanner(System.in);
        pizzaService = new PizzaService(new PizzaStore());
        System.out.println("Hey Buddy, Welcome to Pizza_World`s Spot !!! ");

        while (true) {
            System.out.println("\nChoose your Role to Login :");
            System.out.println("1) ADMIN ");
            System.out.println("2) CUSTOMER ");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = console.nextInt();
            } catch (Exception error) {
                System.out.println("Error : " + error.getClass().getName());
                System.out.println("Try Again !!!");
                console.nextLine();
                continue;
            }
            if (choice == 1) {
                openAdminRole(console, pizzaService);
            } else if (choice == 2) {
                openCustomerRole(console, pizzaService);
            } else {
                System.out.println("Invalid option, Try again !!!");
            }
        }
    }
    private static void openAdminRole(Scanner console, PizzaInterface pizzaService) {
    	System.out.println("\n Welcome to ADMIN console !!!");
        while (true) {
		    try {
		        System.out.println("Choose an Option:");
		        System.out.println("1) Add Pizza");
		        System.out.println("2) Update Pizza");
		        System.out.println("3) Delete Pizza");
		        System.out.println("4) View All Pizza ");
		        System.out.println("5) Search Pizza ");
		        System.out.println("6) Exit ");
		        System.out.print("Enter your choice: ");
		        int choiceOfAdmin = console.nextInt();

		        if (choiceOfAdmin == 1) {
		            addPizza(console, pizzaService);
		        } else if (choiceOfAdmin == 2) {
		            updatePizza(console, pizzaService);
		        } else if (choiceOfAdmin == 3) {
		            deletePizza(console, pizzaService);
		        } else if (choiceOfAdmin == 4) {
		            viewAllPizzas(pizzaService);
		        } else if (choiceOfAdmin == 5) {
		            searchPizza(console, pizzaService);
		        } else if (choiceOfAdmin == 6) {
		            System.out.println("Thank you ADMIN !!!");
		            return;
		        } else {
		            System.out.println("Invalid choice. Please try again.");
		        }
		    } catch (Exception error) {
		        System.out.println("Error : "+error.getClass().getName());
		        System.out.println("Invalid input. Please enter valid data.");
		        console.nextLine(); 
		        continue;
		    }
		}
    }
    private static void openCustomerRole(Scanner console, PizzaInterface pizzaService) {
    	System.out.println("\nWelcome to CUSTOMER console !!!");
        while (true) {
	        try {
	            System.out.println("Enter your doorNumber, street, city, district, state...");
	            int doorNumber = console.nextInt();
	            String street = console.next();
	            String city = console.next();
	            String district = console.next();
	            String state = console.next();

	            System.out.println("Enter your Details as name, email, mobile...");
	            String customerName = console.next();
	            String email = console.next();
	            long mobile = console.nextLong();

	            Customer customer = new Customer(customerName, email, mobile, new Address(doorNumber, street, city, district, state));
	            Address cust=new Address(doorNumber, street, city, district, state);
	            System.out.println("We added you as our New Customer...");
	            System.out.println("Customer DETAILS => ID: "+customer.getCustomerId()+", Name: "+customer.getCustomerName()+", Email: "+customer.getEmail()+", Mobile: "+customer.getMobile());
	            System.out.println(cust.toString());
	            while (true) {
	                System.out.println("1) Order Pizza");
	                System.out.println("2) Pay Bill");
	                System.out.println("3) View All Pizza");
	                System.out.println("4) View your Orders");
	                System.out.println("5) Search Pizza");
	                System.out.println("6) Exit ");
	                System.out.print("Enter your choice: ");
	                int choiceOfUser = console.nextInt();

	                if (choiceOfUser == 1) {
	                    orderPizza(console, pizzaService, customer);
	                } else if (choiceOfUser == 2) {
	                    payBill(pizzaService, customer);
	                } else if (choiceOfUser == 3) {
	                    viewAllPizzas(pizzaService);
	                } else if (choiceOfUser == 4) {
	                    viewOrderHistory(pizzaService,customer);
	                } else if (choiceOfUser == 5) {
	                    searchPizza(console, pizzaService);
	                } else if (choiceOfUser == 6) {
	                    System.out.println("Thank you for visiting!");
	                    return;
	                } else {
	                    System.out.println("Invalid choice. Please try again.");
	                } 
	            }
	        } catch (Exception error) {
	            System.out.println("Invalid input. Please enter valid data.");
	            System.out.println(error.getClass().getName());
	            console.nextLine();
	            return; 
	        }
        }
    }
    private static void addPizza(Scanner console, PizzaInterface pizzaService) {
	    try {
	        System.out.println("======== Add New Pizza Menu ========");
	        System.out.println("");
	        System.out.println("Enter Details as name of Topping, spice level (basic/mediate/full), description to add a new Topping:");
	        String toppingName = console.next();
	        String spiceLevel = console.next();
	        String descriptionTopping = console.next();

	        Topping topping = new Topping(toppingName, spiceLevel, descriptionTopping);

	        System.out.println("Enter Details as name of Base, type (thin/thick), description to add a new PizzaBase:");
	        String pizzaNameBase = console.next();
	        String type = console.next();
	        String descriptionBase = console.next();

	        PizzaBase pizzaBase = new PizzaBase(pizzaNameBase, type, descriptionBase);

	        System.out.println("Enter details as name of Pizza, price, size (small/medium/large) to add a new Pizza:");
	        String pizzaName = console.next();
	        double price = console.nextDouble();
	        String size = console.next();

	        PizzaStore pizzaStore = new PizzaStore();

	        Pizza pizza = pizzaService.addNewPizza(new Pizza(pizzaName, topping, pizzaBase, price, size));
	        pizzaStore.addNewPizza(pizza);
	        System.out.println("\nNew Pizza Added Successfully !!!");
	        System.out.println("");   ///PIZZA NUMBER 1  PIZZA NUMBER HAVE TO ADD !!!!!
	        System.out.println(pizza.toString());
	        System.out.println(topping.toString());
	        System.out.println(pizzaBase.toString());

	    }
	     catch (Exception error) {
	        System.out.println("\nError: " + error.getMessage());
	        System.out.println(error.getClass().getName());
	        return;
	    }
	}
	private static void updatePizza(Scanner console, PizzaInterface pizzaService) {
		System.out.println("=======Update Pizza Menu======");
	    try {
		    System.out.println("Enter PIZZA name :");
		    String pizzaName = console.next();
		    Pizza existsPizza = pizzaService.getPizzaByName(pizzaName);
		    if (existsPizza == null) {
		        System.out.println("Error: Pizza not found.");
		        return;
		    }else{
		    	System.out.println("Enter the NEW price to be updates :");
			    double newPrice = console.nextDouble();
			    Pizza updatedPizza = pizzaService.updatePrice(existsPizza, newPrice);
			    System.out.println("Pizza updated successfully:");
			    System.out.println(updatedPizza.toString());
			    System.out.println(); 
			}  
		} catch (Exception error) {
		    System.out.println("\nError: " + error.getMessage());
		}
	}
	private static void deletePizza(Scanner console, PizzaInterface pizzaService) {
	    System.out.println("======= Delete Pizza Menu =======");
	    try {
	        System.out.println("Enter Pizza name to delete:");
	        String pizzaName = console.next();      
	        Pizza pizzaToDelete = pizzaService.getPizzaByName(pizzaName);
	        
	        if (pizzaToDelete != null) {
	            pizzaService.deletePizza(pizzaToDelete);
	            System.out.println("Pizza deleted successfully.");
	        } else {
	            System.out.println("Pizza not found.");
	        }
	    } catch (Exception error) {
	        System.out.println("\nError: " + error.getMessage());
	        return;
	    }
	}
	private static void viewAllPizzas(PizzaInterface pizzaService) {
		System.out.println("======== View All Pizza Menu ========");
	    List<Pizza> allPizzas = pizzaService.getAllPizzas();
	    if (!allPizzas.isEmpty()) {
	        System.out.println("\nAll Pizzas:");
	        for (Pizza pizza : allPizzas) {
	            System.out.println(pizza.toString());
	        }
	    } else {
	        System.out.println("\nNo pizzas available !!!");
	    }
	}
	private static void searchPizza(Scanner console, PizzaInterface pizzaService) {
	    System.out.println("========Search Pizza Menu=======");
	    System.out.println("");
	    System.out.println("Choose your Search Type :");
	    System.out.println("1) Search by Name");
	    System.out.println("2) Search by ID");
	    System.out.println("3) Search by Size");
	    System.out.print("Enter your choice: ");

	    int searchType = console.nextInt();
	    console.nextLine(); 
	    try {
	        switch (searchType) {
	            case 1:
	                searchByName(console, pizzaService);
	                break;
	            case 2:
	                searchById(console, pizzaService);
	                break;
	            case 3:
	                searchBySize(console, pizzaService);
	                break;
	            default:
	                System.out.println("Invalid choice. Please try again.");
	        }
	    } catch (Exception error) {
	        System.out.println("\nError: " + error.getMessage());
	        System.out.println(error.getClass().getName());
	        return;
	    }
	}
	private static void searchByName(Scanner console, PizzaInterface pizzaService) {
	    System.out.print("Enter Pizza Name to search: ");
	    String pizzaName = console.nextLine();
	    Pizza pizza = null;
	    try {
	        pizza = pizzaService.getPizzaByName(pizzaName);
	    } catch (Exception error) {
	        System.out.println("\nError: " + error.getMessage());
	        return;
	    }
	    if (pizza == null) {
	        System.out.println("\nPizza not found.");
	    } else {
	        System.out.println("\nPizza Found:");
	        System.out.println(pizza.toString());
	        // Adding topping details and PizzaBase details
	    }
	}
	private static void searchById(Scanner console, PizzaInterface pizzaService) {
	    System.out.print("Enter Pizza ID to search: ");
	    int pizzaId = console.nextInt();
	    Pizza pizza = null;
	    try {
	        pizza = pizzaService.getPizzaById(pizzaId);
	    } catch (Exception error) {
	        System.out.println("\nError: " + error.getMessage());
	        return;
	    }
	    if (pizza == null) {
	        System.out.println("\nPizza not found.");
	    } else {
	        System.out.println("\nPizza Found:");
	        System.out.println(pizza.toString());
	    }
	}
	private static void searchBySize(Scanner console, PizzaInterface pizzaService) {
	    System.out.print("Enter Pizza Size to search: ");
	    String size = console.nextLine();
		Pizza pizza = null;
		try {
		    pizza = pizzaService.getPizzaBySize(size);
		} catch (Exception error) {
		    System.out.println("\nError: " + error.getMessage());
		    return;
		}
		if (pizza == null) {
		    System.out.println("\nPizza not found.");
		} else {
		    System.out.println("\nPizza Found:");
		    System.out.println(pizza.toString());
		}
	}
    private static void orderPizza(Scanner console, PizzaInterface pizzaService, Customer customer) {
	    System.out.println("======== Order New Pizza Menu=======");
	    System.out.println("Available Pizzas :");
	    viewAllPizzas(pizzaService);
	    System.out.println("");
	    System.out.print("Enter Pizza Name to Place your Order : ");
	    String pizzaName = console.next();

	    try {
		    Pizza pizza = pizzaService.getPizzaByName(pizzaName);
		    if (pizza == null) {
		        System.out.println("Error: Pizza not found.");
		        return;
		    }else{
		    	System.out.println("Your order has been successfully placed !!!");
			    System.out.println("");
			    System.out.println(pizza.toString());
			    pizzaService.placeOrder(customer, pizza);
			    
		    }
		} catch (Exception error) {
		    System.out.println("Error: " + error.getMessage());
		}
	}
	private static void payBill(PizzaInterface pizzaService, Customer customer) {
	    try {
	        double billAmount = pizzaService.calculateBill(customer);
	        System.out.println("\n======== Check your order's Bill Amount here... =======");
	        System.out.println("Your Payable Bill Amount for all your orders is Rs: " + billAmount + " INR");
	    } catch (Exception error) {
	        System.out.println("Error: " + error.getMessage());
	        return;
	    }
	}
	private static void viewOrderHistory(PizzaInterface pizzaService, Customer customer) {
	    System.out.println("\nOrder History for " + customer.getCustomerName() + ":");
	    List<Order> orderHistory = pizzaService.getOrderHistory(customer);
	    if (orderHistory.isEmpty()) {
	        System.out.println("You have not ordered any pizzas.");
	    } else {
	        for (Order order : orderHistory) {
	            System.out.println("Order ID: " + order.getOrderId() +
	                    ", Date: " + order.getOrderDate() +
	                    ", Bill Amount: $" + order.getPayableBillAmount());
	            for (Pizza pizza : order.getPizzas()) {
	                System.out.println("   " + pizza.toString());
	            }
	            System.out.println();
	        }
	   }
	}	
}


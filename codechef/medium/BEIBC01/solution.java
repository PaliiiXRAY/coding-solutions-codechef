import java.util.Scanner;

// TODO: Create base class CloudResource with calculateMonthlyCost() returning 0.0
class CloudResource{
    public double calculateMonthlyCost(){
        return 0.0;
    }
}

// TODO: Create subclass ComputeNode, override calculateMonthlyCost()
class ComputeNode extends CloudResource{
    private double ratePerHour;
    private int hoursActive;
    public ComputeNode(double ratePerHour, int hoursActive){
        this.ratePerHour = ratePerHour;
        this.hoursActive = hoursActive;

    }
    @Override 
    public double calculateMonthlyCost(){
        return ratePerHour*hoursActive;
    }
}

// TODO: Create subclass StorageBucket, override calculateMonthlyCost()
class StorageBucket extends CloudResource{
   private double ratePerGb;
   private double storageUsed;
   
   public StorageBucket(double ratePerGb, double storageUsed){
       this.ratePerGb = ratePerGb;
       this.storageUsed = storageUsed;
   }
   @Override
   public double calculateMonthlyCost(){
       return ratePerGb*storageUsed;
   }
}

// TODO: Create subclass DatabaseInstance, override calculateMonthlyCost()
class DatabaseInstance extends CloudResource{
   private double baseFee;
   private double connectionRate;
    private int activeConnections;
    public DatabaseInstance(double baseFee, double connectionRate , int activeConnections){
        this.baseFee = baseFee;
        this.connectionRate = connectionRate;
        this.activeConnections = activeConnections;
    }
    @Override
    public double calculateMonthlyCost(){
        return baseFee + (connectionRate*activeConnections);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();

        CloudResource[] resources = new CloudResource[n];

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            
            if (type.equals("COMPUTE")) {
                double rate = scanner.nextDouble();
                int hours = scanner.nextInt();
                // TODO: Initialize resources[i] with a ComputeNode
                resources[i] = new ComputeNode(rate , hours);
            } 
            else if (type.equals("STORAGE")) {
                double rate = scanner.nextDouble();
                double gb = scanner.nextDouble();
                // TODO: Initialize resources[i] with a StorageBucket
                resources[i] = new StorageBucket(rate , gb);
            } 
            else if (type.equals("DATABASE")) {
                double baseFee = scanner.nextDouble();
                double connRate = scanner.nextDouble();
                int conns = scanner.nextInt();
                // TODO: Initialize resources[i] with a DatabaseInstance
                resources[i] = new DatabaseInstance(baseFee, connRate , conns);
            }
        }

        double totalBillingAmount = 0.0;
        
        for (int i = 0; i < n; i++) {
            // TODO: Accumulate costs dynamically by calling the overridden method
            totalBillingAmount += resources[i].calculateMonthlyCost();
        }

        System.out.printf("Total Billing Amount: $%.2f\n", totalBillingAmount);
        scanner.close();
    }
}

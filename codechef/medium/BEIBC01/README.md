# BEIBC01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

## Kargil Exp 2: Cloud Billing Engine (Polymorphism)
## Problem Description

You have been hired by a rapidly growing Cloud Service Provider to overhaul their billing engine. Currently, the legacy code uses massive "if-else" blocks and `instanceof` checks to calculate the monthly cost of different cloud resources.

Your task is to re-architect this system using Inheritance, Method Overriding, and Runtime Polymorphism. You will design a base class called `CloudResource`, and create subclasses for specific services. The main billing engine must be able to iterate through an array of `CloudResource` objects and calculate the total aggregate cost  **without ever checking the specific object type**  (no downcasting, no `instanceof`).

## Architecture

Implement the following architecture:

### 1. Base Class: CloudResource
- Must contain a method: public double calculateMonthlyCost() that returns 0.0 by default.
### 2. Subclass: ComputeNode (extends CloudResource)
- State: double ratePerHour, int hoursActive
- Formula: ratePerHour * hoursActive
### 3. Subclass: StorageBucket (extends CloudResource)
- State: double ratePerGb, double storageUsed
- Formula: ratePerGb * storageUsed
### 4. Subclass: DatabaseInstance (extends CloudResource)
- State: double baseFee, double connectionRate, int activeConnections
- Formula: baseFee + (connectionRate * activeConnections)
## Input Format
- The first line contains an integer N, the number of cloud resources in the customer's account.
- The next N lines contain the resource data. Each line starts with a string representing the resource type (COMPUTE, STORAGE, or DATABASE), followed by its specific pricing parameters separated by spaces. For COMPUTE: COMPUTE [ratePerHour] [hoursActive] For STORAGE: STORAGE [ratePerGb] [storageUsed] For DATABASE: DATABASE [baseFee] [connectionRate] [activeConnections]
## Output Format

Print a single line containing the total aggregate monthly cost formatted to exactly two decimal places.

 **Format:**  `Total Billing Amount: $[TotalCost]`

## Constraints
- 1 <= N <= 100
- All rates, fees, and usages are positive numbers.
### Sample 1:
Input
Output

```
3
COMPUTE 0.5 730
STORAGE 0.02 500
DATABASE 50.0 0.1 100
```

```
Total Billing Amount: $435.00
```

### Explanation:
- ComputeNode: 0.5 * 730 = $365.00
- StorageBucket: 0.02 * 500 = $10.00
- DatabaseInstance: 50.0 + (0.1 * 100) = $60.00
- Total: 365.00 + 10.00 + 60.00 = $435.00

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-03T10:27:12.668Z  

```java
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

```

---

[View on CodeChef](https://www.codechef.com/problems/BEIBC01)
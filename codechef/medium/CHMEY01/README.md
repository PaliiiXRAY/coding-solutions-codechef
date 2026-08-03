# CHMEY01

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

PROBLEM TITLE: Kargil Exp 1 - Null-Safe Financial DTO

PROBLEM DESCRIPTION

You are building a payment processing module for a fintech startup. The system receives transaction data from a legacy banking API. Because the legacy system is unstable, the JSON payloads often have missing fields.

Initially, the team used primitive data types (double, long, boolean) for the Data Transfer Object (DTO). However, this caused two major issues:

- Missing numeric values defaulted to 0.0, which the system processed as actual zero-dollar transactions.
- Attempting to map missing API data to primitives threw NullPointerException (NPE) during auto-unboxing.

Your task is to redesign the TransactionDTO using Wrapper Classes (Double, Long, Boolean) so the system can accurately represent missing data as null. You must also implement safe access methods to handle this data without crashing the application.

TASK REQUIREMENTS

Design a TransactionDTO class with the following specifications:

- Fields (Must use Wrapper classes): transactionId (Type: Long) amount (Type: Double) isInternational (Type: Boolean)
- Methods: public boolean isValid(): Returns true only if both transactionId and amount are NOT null. Otherwise, returns false. public double getSafeAmount(): Returns the primitive double amount if it exists. If the amount is null, it should safely return 0.0 to prevent unboxing NPEs. public String getStatus(): If isInternational is true, return "INTERNATIONAL". If isInternational is false, return "DOMESTIC". If isInternational is null, return "UNKNOWN".

INPUT FORMAT

- The first line contains an integer N, the number of transactions.
- The next N lines contain three space-separated values: transactionId, amount, and isInternational.
- If a value is missing from the API, it will be represented by the string "null".

OUTPUT FORMAT

For each transaction, print a single line in the following format: ID: [id] | Valid: [isValid] | Amount: [safeAmount] | Type: [status]

(If the ID is null, print "ID: null")

CONSTRAINTS

- 1 <= N <= 100
- -100,000 <= amount <= 100,000
### Sample 1:
Input
Output

```
4
1001 500.50 true
1002 null false
null 250.00 null
1004 1000.00 null
```

```
ID: 1001 | Valid: true | Amount: 500.5 | Type: INTERNATIONAL
ID: 1002 | Valid: false | Amount: 0.0 | Type: DOMESTIC
ID: null | Valid: false | Amount: 250.0 | Type: UNKNOWN
ID: 1004 | Valid: true | Amount: 1000.0 | Type: UNKNOWN
```

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-03T09:55:00.126Z  

```java
import java.util.Scanner;



class TransactionDTO {
    // TODO: Declare fields using Wrapper Classes to allow nulls
    private Long transactionId;
    private Double amount;
    private Boolean isInternational;
    
    public TransactionDTO(Long transactionId, Double amount, Boolean isInternational) {
        // TODO: Initialize fields
        this.transactionId = transactionId;
        this.amount = amount;
        this.isInternational = isInternational;
    }

    // TODO: Implement isValid()
    public boolean isValid() {
        if (transactionId != null && amount != null) {
            return true;
        }
        return false;
    }

    // TODO: Implement getSafeAmount() to prevent unboxing NPE
    public double getSafeAmount() {
        if (amount != null){
            return amount;
        }
        return 0.0;
    }

    // TODO: Implement getStatus() to safely check the boolean wrapper
    public String getStatus() {
        if (isInternational == null){
            return "UNKNOWN";
        }
        else if(isInternational == true){
            return "INTERNATIONAL";
        }
        return "DOMESTIC";
    }
    
    // Getter for ID (Used by the main method)
    public Long getTransactionId() {
        return this.transactionId;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;
        
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String idStr = scanner.next();
            String amountStr = scanner.next();
            String intlStr = scanner.next();

            Long id = idStr.equals("null") ? null : Long.parseLong(idStr);
            Double amount = amountStr.equals("null") ? null : Double.parseDouble(amountStr);
            Boolean isIntl = intlStr.equals("null") ? null : Boolean.parseBoolean(intlStr);

            TransactionDTO dto = new TransactionDTO(id, amount, isIntl);

            System.out.println("ID: " + dto.getTransactionId() + 
                               " | Valid: " + dto.isValid() + 
                               " | Amount: " + dto.getSafeAmount() + 
                               " | Type: " + dto.getStatus());
        }
        scanner.close();
    }
}


```

---

[View on CodeChef](https://www.codechef.com/problems/CHMEY01)
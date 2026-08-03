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


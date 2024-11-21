package com.cesarschool.autoline_honda.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummary {

    private List<EmployeeSalesSummary> salesByEmployee;
    private List<MonthlySalesSummary> monthlySales;
    private List<ClientTradeInCredit> clientsWithTradeInCredit;
    private List<AverageSalaryByPosition> averageSalaries;
    private List<UnsoldCar> unsoldCars;
    private List<FuelTypeSales> salesByFuelType;
    private ClientHighestSale highestSaleCustomer;
    private List<SalesByBranch> salesByBranch;
    private List<DiscountedSales> discountedSales;
    private EmployeeSummary employeeSummary;
    private List<CustomerPhones> customersWithMultiplePhones;
    private List<BranchGoals> branchGoals;
    private MostSoldCar mostSoldCar;
    private List<SalesByDateRange> salesInDateRange;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeSalesSummary {
        private String employeeName;
        private int totalSales;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MonthlySalesSummary {
        private String saleMonth;
        private int totalSales;
        private double totalRevenue;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClientTradeInCredit {
        private String cpf;
        private String name;
        private double tradeInValue;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AverageSalaryByPosition {
        private String position;
        private double averageSalary;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnsoldCar {
        private String chassis;
        private String model;
        private double price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FuelTypeSales {
        private String fuelType;
        private int totalSales;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClientHighestSale {
        private String cpf;
        private String name;
        private double finalPrice;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SalesByBranch {
        private String branchName;
        private int totalSales;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DiscountedSales {
        private String saleId;
        private String customerName;
        private String chassis;
        private String model;
        private double discount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmployeeSummary {
        private int totalManagers;
        private int totalSellers;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CustomerPhones {
        private String cpf;
        private String name;
        private int totalPhones;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BranchGoals {
        private String branchName;
        private String goalDate;
        private int carQuantity;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MostSoldCar {
        private String model;
        private int totalSales;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SalesByDateRange {
        private String saleId;
        private String saleDate;
        private double finalPrice;
        private String customerName;
    }
}

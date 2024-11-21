package com.cesarschool.autoline_honda.repository.implementation;

import com.cesarschool.autoline_honda.domain.DashboardSummary;
import com.cesarschool.autoline_honda.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public DashboardSummary getDashboardSummary(String startDate, String endDate) {
        // Consulta 2: Vendas por funcionário
        List<DashboardSummary.EmployeeSalesSummary> salesByEmployee = jdbcTemplate.query(
                "SELECT e.name AS employee_name, COUNT(s.sale_id) AS total_sales " +
                        "FROM Sales s JOIN Employees e ON s.employee_cpf = e.cpf " +
                        "GROUP BY e.name ORDER BY total_sales DESC",
                (rs, rowNum) -> new DashboardSummary.EmployeeSalesSummary(
                        rs.getString("employee_name"),
                        rs.getInt("total_sales")
                )
        );

        // Consulta 3: Total de vendas e receita por mês
        List<DashboardSummary.MonthlySalesSummary> monthlySales = jdbcTemplate.query(
                "SELECT DATE_FORMAT(sale_date, '%Y-%m') AS sale_month, COUNT(sale_id) AS total_sales, SUM(final_price) AS total_revenue " +
                        "FROM Sales GROUP BY sale_month ORDER BY sale_month DESC",
                (rs, rowNum) -> new DashboardSummary.MonthlySalesSummary(
                        rs.getString("sale_month"),
                        rs.getInt("total_sales"),
                        rs.getDouble("total_revenue")
                )
        );

        // Consulta 4: Clientes com Trade-In Credit
        List<DashboardSummary.ClientTradeInCredit> clientsWithTradeInCredit = jdbcTemplate.query(
                "SELECT c.cpf, c.name, t.value AS trade_in_value " +
                        "FROM Customer c JOIN TradeInCredit t ON c.cpf = t.customer_cpf",
                (rs, rowNum) -> new DashboardSummary.ClientTradeInCredit(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getDouble("trade_in_value")
                )
        );

        // Consulta 5: Média salarial por posição
        List<DashboardSummary.AverageSalaryByPosition> averageSalaries = jdbcTemplate.query(
                "SELECT position, AVG(salary) AS average_salary " +
                        "FROM Employees GROUP BY position",
                (rs, rowNum) -> new DashboardSummary.AverageSalaryByPosition(
                        rs.getString("position"),
                        rs.getDouble("average_salary")
                )
        );

        // Consulta 7: Vendas por tipo de combustível
        List<DashboardSummary.FuelTypeSales> salesByFuelType = jdbcTemplate.query(
                "SELECT c.fuel_type, COUNT(s.sale_id) AS total_sales " +
                        "FROM Sales s JOIN Car c ON s.car_chassis = c.chassis " +
                        "GROUP BY c.fuel_type",
                (rs, rowNum) -> new DashboardSummary.FuelTypeSales(
                        rs.getString("fuel_type"),
                        rs.getInt("total_sales")
                )
        );

        // Consulta 9: Vendas por filial
        List<DashboardSummary.SalesByBranch> salesByBranch = jdbcTemplate.query(
                "SELECT b.name AS branch_name, COUNT(s.sale_id) AS total_sales " +
                        "FROM Sales s JOIN Employees e ON s.employee_cpf = e.cpf " +
                        "JOIN Branch b ON e.branch_cnpj = b.cnpj " +
                        "GROUP BY b.name",
                (rs, rowNum) -> new DashboardSummary.SalesByBranch(
                        rs.getString("branch_name"),
                        rs.getInt("total_sales")
                )
        );

        // Consulta 11: Total de gerentes e vendedores
        DashboardSummary.EmployeeSummary employeeSummary = jdbcTemplate.queryForObject(
                "SELECT SUM(CASE WHEN position = 'Gerente' THEN 1 ELSE 0 END) AS total_managers, " +
                        "SUM(CASE WHEN position = 'Vendedor' THEN 1 ELSE 0 END) AS total_sellers " +
                        "FROM Employees",
                (rs, rowNum) -> new DashboardSummary.EmployeeSummary(
                        rs.getInt("total_managers"),
                        rs.getInt("total_sellers")
                )
        );

        // Consulta 12: Clientes com múltiplos telefones
        List<DashboardSummary.CustomerPhones> customersWithMultiplePhones = jdbcTemplate.query(
                "SELECT c.cpf, c.name, COUNT(cp.phone_number) AS total_phones " +
                        "FROM Customer c JOIN CustomerPhone cp ON c.cpf = cp.customer_cpf " +
                        "GROUP BY c.cpf, c.name HAVING total_phones > 1",
                (rs, rowNum) -> new DashboardSummary.CustomerPhones(
                        rs.getString("cpf"),
                        rs.getString("name"),
                        rs.getInt("total_phones")
                )
        );

        // Consulta 13: Metas por filial
        List<DashboardSummary.BranchGoals> branchGoals = jdbcTemplate.query(
                "SELECT b.name AS branch_name, g.goal_date, g.car_quantity " +
                        "FROM Goals g JOIN Branch b ON g.branch_cnpj = b.cnpj " +
                        "ORDER BY g.goal_date DESC",
                (rs, rowNum) -> new DashboardSummary.BranchGoals(
                        rs.getString("branch_name"),
                        rs.getString("goal_date"),
                        rs.getInt("car_quantity")
                )
        );

        // Consulta 15: Vendas em intervalo de datas
        List<DashboardSummary.SalesByDateRange> salesInDateRange = jdbcTemplate.query(
                "SELECT s.sale_id, s.sale_date, s.final_price, c.name AS customer_name " +
                        "FROM Sales s JOIN Customer c ON s.customer_cpf = c.cpf " +
                        "WHERE s.sale_date BETWEEN ? AND ?",
                new Object[]{startDate, endDate},
                (rs, rowNum) -> new DashboardSummary.SalesByDateRange(
                        rs.getString("sale_id"),
                        rs.getString("sale_date"),
                        rs.getDouble("final_price"),
                        rs.getString("customer_name")
                )
        );

        return new DashboardSummary(
                salesByEmployee,
                monthlySales,
                clientsWithTradeInCredit,
                averageSalaries,
                null,  // Unsold cars não está nas consultas
                salesByFuelType,
                null,  // Highest sale customer não está nas consultas
                salesByBranch,
                null,  // Discounted sales não está nas consultas
                employeeSummary,
                customersWithMultiplePhones,
                branchGoals,
                null,  // Most sold car não está nas consultas
                salesInDateRange
        );
    }
}
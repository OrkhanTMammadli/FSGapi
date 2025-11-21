package com.project.FSGapi.DTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class IncomeStatementReport {
    private BigDecimal totalRevenue;
    private BigDecimal totalCostOfGoodsSold;
    private BigDecimal GrossProfit;
    private BigDecimal totalExpenses;
    private BigDecimal PBIT;
    private BigDecimal IncomeTax;
    private BigDecimal InterestExpense;
    private BigDecimal NetProfit;
    private BigDecimal GrossProfitMargin;
    private LocalDate generatedDate;
}

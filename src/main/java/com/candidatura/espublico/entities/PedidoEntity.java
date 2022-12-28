package com.candidatura.espublico.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="pedidos")
public class PedidoEntity {

    @Id
    @Getter @Setter @Column(name="order_id")
    Integer orderId;
    @Getter @Setter @Column(name="region")
    String region;
    @Getter @Setter @Column(name="country")
    String country;
    @Getter @Setter @Column(name="item_type")
    String itemType;
    @Getter @Setter @Column(name="sales_channel")
    String salesChannel;
    @Getter @Setter @Column(name="order_priority")
    String orderPriority;
    @Getter @Setter @Column(name = "order_date")
    Date orderDate;
    @Getter @Setter @Column(name="ship_date")
    Date shipDate;
    @Getter @Setter @Column(name="units_sold")
    Integer unitsSold;
    @Getter @Setter @Column(name="unit_price")
    BigDecimal unitPrice;
    @Getter @Setter @Column(name="unit_cost")
    BigDecimal unitCost;
    @Getter @Setter @Column(name="total_revenue")
    BigDecimal totalRevenue;
    @Getter @Setter @Column(name="total_cost")
    BigDecimal totalCost;
    @Getter @Setter @Column(name="total_profit")
    BigDecimal totalProfit;

    public PedidoEntity() {}
    public PedidoEntity(String[] linea) {
        this.orderId= Integer.valueOf(linea[6]);
        this.region=linea[0];
        this.country=linea[1];
        this.itemType=linea[2];
        this.salesChannel=linea[3];
        this.orderPriority=linea[4];
        this.orderDate=new Date(linea[5]);
        this.shipDate=new Date(linea[7]);
        this.unitsSold= Integer.valueOf(linea[8]);
        this.unitPrice=new BigDecimal(linea[9]);
        this.unitCost=new BigDecimal(linea[10]);
        this.totalRevenue=new BigDecimal(linea[11]);
        this.totalCost=new BigDecimal(linea[12]);
        this.totalProfit=new BigDecimal(linea[13]);
    }
}

package com.candidatura.espublico.entities;

import com.candidatura.espublico.DTO.ResumenDTO;
import com.candidatura.espublico.RESTobjects.FilmPilotShip;
import com.candidatura.espublico.utils.Utils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="pedidos")
@NamedNativeQuery(
        name = "recuentoPorRegion",
        query ="select region as campo,count(*)  as recuento from pedidos group by region order by recuento desc",
        resultSetMapping = "resumenDTO"
)
@NamedNativeQuery(
        name = "recuentoPorPais",
        query ="select country as campo,count(*)  as recuento from pedidos group by country order by recuento desc",
        resultSetMapping = "resumenDTO"
)
@NamedNativeQuery(
        name = "recuentoPorItem",
        query ="select item_type as campo,count(*)  as recuento from pedidos group by item_type order by recuento desc",
        resultSetMapping = "resumenDTO"
)
@NamedNativeQuery(
        name = "recuentoPorChannel",
        query ="select sales_channel as campo,count(*)  as recuento from pedidos group by sales_channel order by recuento desc",
        resultSetMapping = "resumenDTO"
)
@NamedNativeQuery(
        name = "recuentoPorPriority",
        query ="select order_priority as campo,count(*)  as recuento from pedidos group by order_priority order by recuento desc",
        resultSetMapping = "resumenDTO"
)
@SqlResultSetMapping(
        name = "resumenDTO",
        classes = @ConstructorResult(
                targetClass = ResumenDTO.class,
                columns = {
                        @ColumnResult(name = "campo", type = String.class),
                        @ColumnResult(name = "recuento", type = Integer.class)
                }
        )
)
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
        this.region=linea[0];
        this.country=linea[1];
        this.itemType=linea[2];
        this.salesChannel=linea[3];
        this.orderPriority=linea[4];
        this.orderDate=Utils.parseDate(linea[5]);
        this.orderId= Integer.valueOf(linea[6]);
        this.shipDate=Utils.parseDate(linea[7]);
        this.unitsSold= Integer.valueOf(linea[8]);
        this.unitPrice=new BigDecimal(linea[9]);
        this.unitCost=new BigDecimal(linea[10]);
        this.totalRevenue=new BigDecimal(linea[11]);
        this.totalCost=new BigDecimal(linea[12]);
        this.totalProfit=new BigDecimal(linea[13]);
    }

    public String toString(){
        String result = "";
        result = this.region+","+this.country+","+this.itemType+","+this.salesChannel+","+this.orderPriority+","+ Utils.formatDate(this.orderDate)+","+this.orderId+","+Utils.formatDate(this.shipDate)+","+this.unitsSold+","+this.unitPrice+","+this.unitCost+","+this.totalRevenue+","+this.totalCost+","+this.totalProfit;
        return result;
    }
}

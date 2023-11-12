package christmas.order;

public class Order {

    private final VisitDate visitDate;
    private final OrderMenus orderMenus;

    public Order(VisitDate visitDate, OrderMenus orderMenus) {
        this.visitDate = visitDate;
        this.orderMenus = orderMenus;
    }

    public int getOrderTotalPrice() {
        return orderMenus.getTotalPrice();
    }
}

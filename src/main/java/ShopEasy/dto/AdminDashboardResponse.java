package ShopEasy.dto;

import java.math.BigDecimal;

public class AdminDashboardResponse {

    private long totalCustomers;
    private long activeCustomers;

    private long totalProducts;
    private long activeProducts;

    private long totalOrders;
    private long pendingOrders;
    private long confirmedOrders;
    private long shippedOrders;
    private long outForDeliveryOrders;
    private long deliveredOrders;
    private long cancelledOrders;

    private BigDecimal totalRevenue;


    public AdminDashboardResponse() {
    }


    public AdminDashboardResponse(
            long totalCustomers,
            long activeCustomers,
            long totalProducts,
            long activeProducts,
            long totalOrders,
            long pendingOrders,
            long confirmedOrders,
            long shippedOrders,
            long outForDeliveryOrders,
            long deliveredOrders,
            long cancelledOrders,
            BigDecimal totalRevenue) {

        this.totalCustomers = totalCustomers;
        this.activeCustomers = activeCustomers;
        this.totalProducts = totalProducts;
        this.activeProducts = activeProducts;
        this.totalOrders = totalOrders;
        this.pendingOrders = pendingOrders;
        this.confirmedOrders = confirmedOrders;
        this.shippedOrders = shippedOrders;
        this.outForDeliveryOrders = outForDeliveryOrders;
        this.deliveredOrders = deliveredOrders;
        this.cancelledOrders = cancelledOrders;
        this.totalRevenue = totalRevenue;
    }


    public long getTotalCustomers() {
        return totalCustomers;
    }

    public long getActiveCustomers() {
        return activeCustomers;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public long getActiveProducts() {
        return activeProducts;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public long getPendingOrders() {
        return pendingOrders;
    }

    public long getConfirmedOrders() {
        return confirmedOrders;
    }

    public long getShippedOrders() {
        return shippedOrders;
    }

    public long getOutForDeliveryOrders() {
        return outForDeliveryOrders;
    }

    public long getDeliveredOrders() {
        return deliveredOrders;
    }

    public long getCancelledOrders() {
        return cancelledOrders;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }
}
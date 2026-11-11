package com.telusko.creatorstore.services;

import com.telusko.creatorstore.dtos.OrderItemRequest;
import com.telusko.creatorstore.dtos.OrderRequest;
import com.telusko.creatorstore.entities.Order;
import com.telusko.creatorstore.entities.OrderItem;
import com.telusko.creatorstore.entities.Product;
import com.telusko.creatorstore.enums.Status;
import com.telusko.creatorstore.repositories.OrderRepository;
import com.telusko.creatorstore.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Order addNewOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .customerName(orderRequest.getCustomerName())
                .customerEmail(orderRequest.getCustomerEmail())
                .status(Status.CONFIRMED)
                .build();

        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemRequest o : orderRequest.getItems()) {
            Product p = productRepository.findById(o.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product with given id: " + o.getProductId() + " not found"));
            if (o.getQuantity() > p.getStockQuantity())
                throw new RuntimeException("Requested quantity can not be fulfilled at the moment");
            // saved automatically when the transaction commits
            p.setStockQuantity(p.getStockQuantity() - o.getQuantity());
            totalPrice = totalPrice.add(p.getPrice().multiply(BigDecimal.valueOf(o.getQuantity())));

            OrderItem orderItem = OrderItem.builder()
                    .product(p)
                    .quantity(o.getQuantity())
                    .itemPriceAtPurchase(p.getPrice())
                    .order(order)
                    .build();
            order.getItems().add(orderItem);
        }
        order.setTotalPrice(totalPrice);
        // cascade = ALL saves the items along with the order
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with given id: " + id + " not found"));
    }

}

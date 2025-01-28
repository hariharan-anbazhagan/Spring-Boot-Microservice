package com.hariSolution.Order;

import com.hariSolution.KafkaProducer.OrderConfirmation;
import com.hariSolution.KafkaProducer.OrderProducer;
import com.hariSolution.User.UserClient;
import com.hariSolution.User.UserDetails;
import com.hariSolution.OrderItem.OrderItemRequest;
import com.hariSolution.OrderItem.OrderItemService;
import com.hariSolution.OrderItem.OrderRepository;
import com.hariSolution.payment.PaymentClient;
import com.hariSolution.payment.PaymentRequest;
import com.hariSolution.productInfo.ProductClient;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ProductClient productClient;
    private final UserClient userClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final OrderProducer orderProducer;

    public OrderService(ProductClient productClient, UserClient userClient, PaymentClient paymentClient, OrderRepository orderRepository, OrderMapper orderMapper, OrderItemService orderItemService, OrderProducer orderProducer) {
        this.productClient = productClient;
        this.userClient = userClient;
        this.paymentClient = paymentClient;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderItemService = orderItemService;
        this.orderProducer = orderProducer;
    }

    @Transactional
    public Integer createOrder(OrderRequest request) {

        // Step 1: Fetch User Details
        UserDetails userDetails = this.userClient.getUserDetailsByUserId(request.getUserId());
        if (userDetails == null) {
            throw new RuntimeException("User not found for ID: " + request.getUserId());
        }

        // Step 2: Purchase Products
        var purchasedProducts = productClient.purchaseProducts(request.getProducts());
        if (purchasedProducts == null || purchasedProducts.isEmpty()) {
            throw new RuntimeException("Failed to purchase products for request: " + request.getProducts());
        }

        // Step 3: Save Order
        var order = this.orderRepository.save(this.orderMapper.toOrder(request));

        System.out.println(order);
        // Step 4: Create Order Items
        request.getProducts().forEach(purchasedProduct -> {
            OrderItemRequest orderItemRequest = new OrderItemRequest();
            orderItemRequest.setOrderId(order.getId());
            orderItemRequest.setProductId(purchasedProduct.getProductId());
            orderItemRequest.setQuantity(purchasedProduct.getQuantity());

            orderItemService.createOrderItem(orderItemRequest);
        });

        System.out.println(orderItemService);

        // Step 5: Make Payment
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(request.getAmount());
        paymentRequest.setOrderReference(request.getReference());
        paymentRequest.setPaymentMethod(request.getPaymentMethod());
        paymentRequest.setOrderId(order.getId());
        paymentRequest.setUser(userDetails);

        // Call payment service
        try {
            this.paymentClient.getPaymentDetailsFromPaymentService(paymentRequest);
        } catch (Exception e) {
            throw new RuntimeException("Payment service failed: " + e.getMessage(), e);
        }


        // Step 6: Produce Order Confirmation Event
        OrderConfirmation orderConfirmation = new OrderConfirmation();
        orderConfirmation.setOrderReference(request.getReference());
        orderConfirmation.setUser(userDetails);
        orderConfirmation.setProducts(purchasedProducts);
        orderConfirmation.setPaymentMethod(request.getPaymentMethod());
        orderConfirmation.setTotalAmount(request.getAmount());
        System.out.println(orderConfirmation);

       orderProducer.orderConfirmation(orderConfirmation);

        // Step 7: Return Order ID
        return order.getId();
    }



}

package com.hariSolution.order;

import com.hariSolution.User.UserClient;
import com.hariSolution.mapper.OrderDetailsResponseMapper;
import com.hariSolution.mapper.OrderMapper;
import com.hariSolution.orderIntem.OrderItemRequest;
import com.hariSolution.orderIntem.OrderItemService;
import com.hariSolution.payment.PaymentClient;
import com.hariSolution.payment.PaymentRequest;
import com.hariSolution.kafka.Producer.OrderConfirmation;
import com.hariSolution.kafka.Producer.OrderProducer;
import com.hariSolution.product.ProductClient;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;
    private final PaymentClient paymentClient;
    private final OrderMapper orderMapper;
    private final OrderItemService orderItemService;
    private final OrderDetailsResponseMapper orderDetailsResponseMapper;
    private final OrderProducer orderProducer;

    @Transactional
    public OrderDetailsResponse createOrder(@Valid OrderRequest request) {
        if (request.getUserId() == null) {
            return this.orderDetailsResponseMapper.createResponse(
                    null, "User ID is null", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST
            );
        }

        var user = this.userClient.getUserDetailsByUserId(request.getUserId());

        System.out.println(user);
        if (user == null) {
            return this.orderDetailsResponseMapper.createResponse(
                    null, "User details not found", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST
            );
        }

        var purchasedProducts = productClient.ProductPurchase(request.getProduct());
        if (purchasedProducts == null) {
            throw new RuntimeException("Failed to purchase products for request: " + request.getProduct());
        }
        System.out.println(purchasedProducts);

        Order order = this.orderRepository.save(this.orderMapper.toOrder(request));

        if (order.getId() == null) {
            throw new RuntimeException("Order creation failed.");
        }

        OrderItemRequest orderItemRequest = new OrderItemRequest();
        orderItemRequest.setOrderId(order.getId());
        orderItemRequest.setProductId(purchasedProducts.getProductId());
        orderItemRequest.setProductName(purchasedProducts.getName());
        orderItemRequest.setQuantity(purchasedProducts.getQuantity());

        this.orderItemService.createOrderItem(orderItemRequest);

        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setOrderReference(request.getReference());
        paymentRequest.setPaymentMethod(request.getPaymentMethod());
        paymentRequest.setAmount(request.getTotalAmount());
        paymentRequest.setOrderId(order.getId());
        paymentRequest.setUserId(request.getUserId());
        System.out.println(paymentRequest);

        try {
            this.paymentClient.getPaymentDetailsFromPaymentService(paymentRequest);
        } catch (Exception e) {
            throw new RuntimeException("Payment service failed: " + e.getMessage(), e);
        }

        OrderConfirmation orderConfirmation = new OrderConfirmation();
        orderConfirmation.setUser(user);
        orderConfirmation.setTotalAmount(order.getTotalAmount());
        orderConfirmation.setPaymentMethod(paymentRequest.getPaymentMethod());
        orderConfirmation.setOrderReference(order.getReference());
        orderConfirmation.setProducts(purchasedProducts);

        System.out.println(orderConfirmation);

       this.orderProducer.sendOrderConfirmationMessage(orderConfirmation);

        OrderResponse response = this.orderMapper.toOrderResponse(order);
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("OrderDetails", response);

        return this.orderDetailsResponseMapper.createResponse(data, "Successfully processed", HttpStatus.OK.value(), HttpStatus.OK);
    }

    public OrderDetailsResponse getAllOrderDetails() {
        List<Order> orders=this.orderRepository.findAll();
        LinkedHashMap<String,Object>data=new LinkedHashMap<>();

        orders.forEach(order->{
           OrderResponse response=this.orderMapper.toOrderResponse(order);
            data.put("orderDetails :"+order.getId(),response);
        });

        return this.orderDetailsResponseMapper.createResponse(data,"Successfully data feached!!",HttpStatus.OK.value(), HttpStatus.OK);
    }
}

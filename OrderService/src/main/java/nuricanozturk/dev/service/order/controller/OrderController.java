package nuricanozturk.dev.service.order.controller;

import nuricanozturk.dev.service.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController
{
    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestParam("bid") UUID bookId, @RequestParam("uid") UUID userId)
    {
        orderService.buyBook(bookId, userId);
        return ResponseEntity.ok(true);
    }
}

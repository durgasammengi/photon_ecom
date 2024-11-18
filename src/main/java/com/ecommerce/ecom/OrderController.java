package com.ecommerce.ecom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.ecommerce.dao.Product;
import com.ecommerce.service.ProductNotFount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dao.Order;
import com.ecommerce.dao.OrderRepository;
import com.ecommerce.service.OrderRequest;
import com.ecommerce.dao.ProductRepository;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private  com.ecommerce.dao.OrderRepository orderRepo;

    @Autowired
    private ProductRepository productRepo;
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
        //validate products
        List<Product> productList = new ArrayList<>();
        for(Long productId : orderRequest.getProductIds()) {
           Product p =productRepo.findById(productId).orElseThrow(() -> new ProductNotFount("Product not found"));
           productList.add(p);
        }
        
        Order order = new Order();
        order.setName(orderRequest.getCustomerName());
        order.setOrderDate(LocalDateTime.now());
        order.setProducts(productList);
        orderRepo.save(order);
        return ResponseEntity.ok(order);


}
@GetMapping("/getOrderFormat")
public ResponseEntity<OrderRequest> checkFormat(){
    OrderRequest order = new OrderRequest();
    order.setCustomerName("Prasad");
    order.setProductIds(Arrays.asList(1L));
        return new ResponseEntity(order, HttpStatus.OK);

}
}

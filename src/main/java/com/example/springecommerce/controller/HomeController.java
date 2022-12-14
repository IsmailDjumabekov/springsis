package com.example.springecommerce.controller;

import com.example.springecommerce.model.DetailOrder;
import com.example.springecommerce.model.Order;
import com.example.springecommerce.model.Product;
import com.example.springecommerce.model.User;
import com.example.springecommerce.service.ProductService;
import com.example.springecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    List<DetailOrder> detail = new ArrayList<DetailOrder>();

    Order order = new Order();

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("products",productService.findAll());
        return "user/home";
    }
    @GetMapping("producthome/{id}")
    public String productHome(@PathVariable Integer id, Model model){
        log.info("Id product sent as parameter {}",id);
        Product product = new Product();
        Optional<Product> productOptional = productService.get(id);
        product = productOptional.get();
        model.addAttribute("product", product);
        return "user/producthome";
    }
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer quantity,Model model){
        DetailOrder detailOrder = new DetailOrder();
        Product product = new Product();
        double sumTotal = 0;
        Optional<Product> optionalProduct = productService.get(id);
        log.info("Product added: {}", optionalProduct.get());
        log.info("Quantity: {}", quantity);
        product = optionalProduct.get();
        detailOrder.setQuantity(quantity);
        detailOrder.setPrice(product.getPrice());
        detailOrder.setName(product.getName());
        detailOrder.setTotal(product.getPrice() * quantity);
        detailOrder.setProduct(product);

        Integer idProduct = product.getId();
        boolean entered = detail.stream().anyMatch(p -> p.getProduct().getId() == idProduct);
        if(!entered){
            detail.add(detailOrder);
        }

        detail.add(detailOrder);

        sumTotal = detail.stream().mapToDouble(dt -> dt.getTotal()).sum();

        order.setTotal(sumTotal);
        model.addAttribute("cart", detail);
        model.addAttribute("order", order);

        return "user/cart";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductCart(@PathVariable Integer id, Model model) {
        List<DetailOrder> detailOrderList = new ArrayList<DetailOrder>();
        for(DetailOrder detailOrder : detail){
            if(detailOrder.getProduct().getId() != id){
                detailOrderList.add(detailOrder);
            }
        }
        detail = detailOrderList;
        double sumTotal = 0;

        sumTotal = detail.stream().mapToDouble(dt -> dt.getTotal()).sum();

        order.setTotal(sumTotal);
        model.addAttribute("cart", detail);
        model.addAttribute("order", order);
        return "user/cart";
    }
    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart", detail);
        model.addAttribute("order", order);
        return "/user/cart";
    }
    @GetMapping("/order")
    public String order(Model model){
        User user = userService.findById(1).get();
        model.addAttribute("cart", detail);
        model.addAttribute("order", order);
        model.addAttribute("user",user);
        return "user/summarize";
    }
}

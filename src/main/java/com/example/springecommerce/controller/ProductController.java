package com.example.springecommerce.controller;

import com.example.springecommerce.model.Product;
import com.example.springecommerce.model.User;
import com.example.springecommerce.service.ProductService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("products",productService.findAll());
        return "products/show";
    }
    @GetMapping("/create")
    public String create(){
        return "products/create";
    }
    @PostMapping("/save")
    public String save(Product product){
        LOGGER.info("Product save successfuly {}", product);
        User user = new User(1,"","","","","","","");
        product.setUser(user);
        productService.save(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model){
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();
        LOGGER.info("Product search {}" , product);
        model.addAttribute("product", product);
        return "products/edit";
    }
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        productService.delete(id);
        return "redirect:/products";
    }
}

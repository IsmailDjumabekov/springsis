package com.example.springecommerce.controller;

import com.example.springecommerce.model.Product;
import com.example.springecommerce.model.User;
import com.example.springecommerce.service.ProductService;
import com.example.springecommerce.service.UploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @Autowired
    private UploadFileService uploadFileService;

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
    public String save(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        LOGGER.info("Product save successfuly {}", product);
        User user = new User(1,"","","","","","","");
        if(product.getId()==null){
            String nameImage = uploadFileService.saveImage(file);
            product.setImage(nameImage);
        }else{

        }
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
    public String update(Product product,@RequestParam("img") MultipartFile file) throws IOException{
        Product p = new Product();
        p = productService.get(product.getId()).get();
        if (file.isEmpty()){
            product.setImage(p.getImage());
        }else{

            if(p.getImage().equals("default.png")){
                uploadFileService.deleteImage(p.getImage());
            }

            String nameImage = uploadFileService.saveImage(file);
            product.setImage(nameImage);
        }
        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/products";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Product p = new Product();
        p = productService.get(id).get();

        if(p.getImage().equals("default.png")){
            uploadFileService.deleteImage(p.getImage());
        }
        productService.delete(id);
        return "redirect:/products";
    }
}

package com.bookstore.controller;

import com.bookstore.domain.Product;
import com.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("market")
@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("products/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String categoryName) {
        model.addAttribute("products", productService.getProductsByCategory(categoryName));
        return "products";
    }

    @RequestMapping("products/filter/{params}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("product")
    public String getProductById(@RequestParam("id") String productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping(value = "products/add", method = RequestMethod.GET)
    public String getAddProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "products/add", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute("newProduct") Product newProduct) {
        productService.addProduct(newProduct);
        return "redirect:/market/products";
    }
}
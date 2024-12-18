package com.codegym.controller;

import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService productService;

	@GetMapping
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("list");
		List<Product> products = productService.findAll();
		mv.addObject("products", products);
		return mv;
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("product", new Product());
		return "create";
	}
	@PostMapping("/save")
	public String save(Product product, RedirectAttributes redirectAttributes) {
		do {
			product.setId((int) (Math.random() * 10000));
		} while (productService.findById(product.getId()) != null);
		productService.add(product);
		redirectAttributes.addFlashAttribute("success", "Product successfully added");
		return "redirect:/products";
	}

	@GetMapping("/{id}/edit")
	public String showEditForm(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.findById(id));
		return "edit";
	}
	@PostMapping("/update")
	public String update(Product product, RedirectAttributes redirectAttributes) {
		productService.update(product);
		redirectAttributes.addFlashAttribute("success", "Product successfully updated");
		return "redirect:/products";
	}

	@GetMapping("/{id}/view")
	public String view(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "view";
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "delete";
	}
	@PostMapping("/{id}/remove")
	public String remove(@PathVariable int id, RedirectAttributes redirectAttributes) {
		productService.remove(id);
		redirectAttributes.addFlashAttribute("success", "Product deleted");
		return "redirect:/products";
	}

	@GetMapping("/search")
	public String search(@RequestParam String name, Model model) {
		List<Product> products = productService.findAll();
		List<Product> results = new ArrayList<>();
		for (Product product : products) {
			if (product.getName().toLowerCase().contains(name.toLowerCase())) {
				results.add(product);
			}
		}
		model.addAttribute("products", results);
		return "list";
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.controllers;

import com.example.Website.dto.Customer;
import com.example.Website.dto.Cart;
import com.example.Website.model.ProductModel;
import com.example.Website.dto.Product;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Hoang Xoan
 */
@SessionAttributes({"nowquantity", "listcart", "ship_price", "totalcost", "currentpage","customer"})
@Controller
public class ProductController {

    private int cateId;

    private ProductModel prodModel;
    private int limit = 12;
    private int page = 1;
    int ship_price = 30000;
    private double totalcost;

    // detail product
    @RequestMapping("/product/{id}")
    public String showProductDetail(@PathVariable(name = "id") String id, Model model) throws Exception {

        ProductModel prodModel = new ProductModel();
        List<Product> prod = prodModel.findById(Integer.parseInt(id));
//        System.out.println("prod" + prod.toString());
        model.addAttribute("prod", prod);

        List<Product> randProducts = prodModel.getRandProduct(4);
        model.addAttribute("randProducts", randProducts);

        return "ProductDetail";

    }

    @PostMapping("/cart_add/{id}")
    public String carthandle(Model model, @PathVariable(name = "id") String id,
            @ModelAttribute Cart cart, HttpSession session, RedirectAttributes ra, HttpServletResponse response) throws Exception {
        ProductModel prodModel = new ProductModel();
        List<Cart> listcart = null;
        Customer cus = (Customer) session.getAttribute("customer");
        List<Cart> list = (List<Cart>) session.getAttribute("listcart");
//        if (list != null) {
//            listcart = list;
//        } else {
//            listcart = new ArrayList<>();
//        }
//        if (session.getAttribute("totalcost") != null) {
//            totalcost = (long) (session.getAttribute("totalcost"));
//        } else {
//            totalcost = 0;
//        }
        Product product = (Product) prodModel.findById(Integer.parseInt(id));
        Cart c = new Cart();
        c.setCustId(cus.getId());
       c.setProductId(product.getId());
        System.out.println(product.getId()+ "hello");
//        c.setImage(product.getImage());
//        c.setName(product.getName());

        c.setQuantity(cart.getQuantity());
//        c.setCost(cart.getCost());
//        c.setTotalcost_id(c.getCost() * cart.getQuantity());

//        totalcost += c.getTotalcost_id();
        listcart.add(c);
        model.addAttribute("ship_price", ship_price);
        model.addAttribute("listcart", listcart);
//        model.addAttribute("totalcost", totalcost);
        model.addAttribute("nowquantity", 0);
        ra.addFlashAttribute("success", "Thêm thành công");
        return "redirect:/viewCart" ;

    }

//    @PostMapping("/search")
//    public String search(Model model, @ModelAttribute Product product, RedirectAttributes reat) throws Exception {
//        ProductModel prodModel = new ProductModel();
//        Product f = prodModel.countSearch(product.getName());
//        if (f.getId() > 0) {
//            return "redirect:/food/" + f.getId();
//        }
//        else{
//            reat.addFlashAttribute("error_search", "Không tìm thấy đồ ăn này,thử món khác!");
//            return "redirect:/";
//        }
//    }
// danh mục hoa quả
    @RequestMapping("/category_fruit")
    public String category(Model model, HttpServletRequest request, RedirectAttributes redirect) {
        request.getSession().setAttribute("cateFruitList", null);
        request.getSession().setAttribute("cateFruit_10_30List", null);

        if (model.asMap().get("success") != null) {
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        }
        return "redirect:/category_fruit/page/1";

    }

    @RequestMapping("/category_fruit/page/{pageNumber}")
    public String showCategoryFruit(HttpServletRequest request,
            @PathVariable int pageNumber, Model model) throws Exception {
        //lay sp ngau nhien o ben trai

        ProductModel prodModel = new ProductModel();
        List<Product> randFruit = prodModel.getRandFruit(3);
        model.addAttribute("randFruit", randFruit);
        // lay tat ca san pham
//        int count = prodModel.count();
//        System.out.println(count);

        cateId = 1;
        int pagesize = 12;

        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cateFruitList");

        List<Product> list = (List<Product>) prodModel.getProductFruit();

        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cateFruitList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/category_fruit/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("category_fruit", pages);

        //loc theo gia 10_30
        int count10_30 = prodModel.countProduct10_30(cateId);
        if (count10_30 > 0) {
            List<Product> fruit10_30 = prodModel.getProduct10_30(cateId);

            model.addAttribute("fruit10_30", fruit10_30);
        } else {
            model.addAttribute("Fail_fruit10_30", true);
        }

        int count_30_50 = prodModel.countProduct30_50(cateId);
        if (count_30_50 > 0) {
            List<Product> fruit30_50 = prodModel.getProduct30_50(cateId);
            model.addAttribute("fruit30_50", fruit30_50);
        } else {
            model.addAttribute("Fail_fruit30_50", true);
        }

        int count50_100 = prodModel.countProduct50_100(cateId);
        if (count50_100 > 0) {
            List<Product> fruit50_100 = prodModel.getProduct50_100(cateId);
            model.addAttribute("fruit50_100", fruit50_100);
        } else {
            model.addAttribute("Fail_fruit50_100", true);
        }
        int count100_200 = prodModel.countProduct100_200(cateId);
        if (count100_200 > 0) {
            List<Product> fruit100_200 = prodModel.getProduct100_200(cateId);
            model.addAttribute("fruit100_200", fruit100_200);
        } else {
            model.addAttribute("Fail_fruit100_200", true);
        }

        int count_biger200 = prodModel.countProductBiger200(cateId);
        if (count_biger200 > 0) {
            List<Product> fruitbiger200 = prodModel.getProductBiger200(cateId);
            model.addAttribute("fruitbiger200", fruitbiger200);
        } else {
            model.addAttribute("Fail_fruitbiger200", true);
        }

        return "Category_Fruit";
    }

    //lấy vegestable
    @RequestMapping("/category_vegetable")
    public String cateVegetable(Model model, HttpServletRequest request, RedirectAttributes redirect) {

        request.getSession().setAttribute("cateVegestableList", null);
        if (model.asMap().get("success") != null) {
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        }
        return "redirect:/category_vegetable/page/1";

    }

    @RequestMapping("/category_vegetable/page/{pageNumber}")
    public String showCateVegetablePage(HttpServletRequest request,
            @PathVariable int pageNumber, Model model) throws Exception {

        ProductModel prodModel = new ProductModel();
        List<Product> randFruit = prodModel.getRandVegetable(3);
        model.addAttribute("randVegetable", randFruit);

        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cateVegestableList");
        int pagesize = 12;

        List<Product> list = (List<Product>) prodModel.getProductVegetable();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cateVegestableList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/category_vegetable/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("category_vegetable", pages);

        cateId = 2;

        //loc theo gia 10_30
        int count10_30 = prodModel.countProduct10_30(cateId);
        if (count10_30 > 0) {
            List<Product> prod10_30 = prodModel.getProduct10_30(cateId);
            model.addAttribute("vegetable10_30", prod10_30);
        } else {
            model.addAttribute("Fail_vegetable10_30", true);
        }

        int count_30_50 = prodModel.countProduct30_50(cateId);
        if (count_30_50 > 0) {
            List<Product> prod30_50 = prodModel.getProduct30_50(cateId);
            model.addAttribute("vegetable30_50", prod30_50);
        } else {
            model.addAttribute("Fail_vegetable30_50", true);
        }

        int count50_100 = prodModel.countProduct50_100(cateId);
        if (count50_100 > 0) {
            List<Product> prod50_100 = prodModel.getProduct50_100(cateId);
            model.addAttribute("vegetable50_100", prod50_100);
        } else {
            model.addAttribute("Fail_vegetable50_100", true);
        }
        int count100_200 = prodModel.countProduct100_200(cateId);
        if (count100_200 > 0) {
            List<Product> prod100_200 = prodModel.getProduct100_200(cateId);
            model.addAttribute("vegetable100_200", prod100_200);
        } else {
            model.addAttribute("Fail_vegetable100_200", true);
        }

        int count_biger200 = prodModel.countProductBiger200(cateId);
        if (count_biger200 > 0) {
            List<Product> prodbiger200 = prodModel.getProductBiger200(cateId);
            model.addAttribute("vegetablebiger200", prodbiger200);
        } else {
            model.addAttribute("Fail_vegetablebiger200", true);
        }

        return "Category_Vegetable";
    }

    //lay các loại hạt
    @RequestMapping("/category_typeOfNut")
    public String cateNut(Model model, HttpServletRequest request, RedirectAttributes redirect) {

        request.getSession().setAttribute("cateNutList", null);
        if (model.asMap().get("success") != null) {
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        }
        return "redirect:/category_typeOfNut/page/1";

    }

    @RequestMapping("/category_typeOfNut/page/{pageNumber}")
    public String showCateNutPage(HttpServletRequest request,
            @PathVariable int pageNumber, Model model) throws Exception {

        ProductModel prodModel = new ProductModel();
        List<Product> randNut = prodModel.getRandNut(3);
        model.addAttribute("randNut", randNut);

        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cateNutList");
        int pagesize = 12;

        List<Product> list = (List<Product>) prodModel.getProductNut();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cateNutList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/category_typeOfNut/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("category_typeOfNut", pages);

        cateId = 3;

        //loc theo gia 10_30
        int count10_30 = prodModel.countProduct10_30(cateId);
        if (count10_30 > 0) {
            List<Product> prod10_30 = prodModel.getProduct10_30(cateId);
            model.addAttribute("TypeOfNut10_30", prod10_30);
        } else {
            model.addAttribute("Fail_TypeOfNut10_30", true);
        }
        System.out.println("hello" + count10_30);
        int count_30_50 = prodModel.countProduct30_50(cateId);
        if (count_30_50 > 0) {
            List<Product> prod30_50 = prodModel.getProduct30_50(cateId);
            model.addAttribute("TypeOfNut30_50", prod30_50);
        } else {
            model.addAttribute("Fail_TypeOfNut30_50", true);
        }

        int count50_100 = prodModel.countProduct50_100(cateId);
        if (count50_100 > 0) {
            List<Product> prod50_100 = prodModel.getProduct50_100(cateId);
            model.addAttribute("TypeOfNut50_100", prod50_100);
        } else {
            model.addAttribute("Fail_TypeOfNut50_100", true);
        }
        int count100_200 = prodModel.countProduct100_200(cateId);
        if (count100_200 > 0) {
            List<Product> prod100_200 = prodModel.getProduct100_200(cateId);
            model.addAttribute("TypeOfNut100_200", prod100_200);
        } else {
            model.addAttribute("Fail_TypeOfNut100_200", true);
        }

        int count_biger200 = prodModel.countProductBiger200(cateId);
        if (count_biger200 > 0) {
            List<Product> prodbiger200 = prodModel.getProductBiger200(cateId);
            model.addAttribute("TypeOfNutbiger200", prodbiger200);
        } else {
            model.addAttribute("Fail_TypeOfNutbiger200", true);
        }

        return "Category_TypeOfNut";
    }

    @RequestMapping("/category_spFood")
    public String cateSpFood(Model model, HttpServletRequest request, RedirectAttributes redirect) {

        request.getSession().setAttribute("cateSpFoodList", null);
        if (model.asMap().get("success") != null) {
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        }
        return "redirect:/category_spFood/page/1";

    }

    @RequestMapping("/category_spFood/page/{pageNumber}")
    public String showCateSpFoodPage(HttpServletRequest request,
            @PathVariable int pageNumber, Model model) throws Exception {

        ProductModel prodModel = new ProductModel();
        List<Product> randSpFood = prodModel.getRandSpFood(3);
        model.addAttribute("randSpFood", randSpFood);

        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cateSpFoodList");
        int pagesize = 12;

        List<Product> list = (List<Product>) prodModel.getProductSpFood();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cateSpFoodList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/category_typeOfNut/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("category_spFood", pages);

        cateId = 4;

        //loc theo gia 10_30
        int count10_30 = prodModel.countProduct10_30(cateId);
        if (count10_30 > 0) {
            List<Product> prod10_30 = prodModel.getProduct10_30(cateId);
            model.addAttribute("SpecialFood10_30", prod10_30);
        } else {
            model.addAttribute("Fail_SpecialFood10_30", true);
        }

        int count_30_50 = prodModel.countProduct30_50(cateId);
        if (count_30_50 > 0) {
            List<Product> prod30_50 = prodModel.getProduct30_50(cateId);
            model.addAttribute("SpecialFood30_50", prod30_50);
        } else {
            model.addAttribute("Fail_SpecialFood30_50", true);
        }

        int count50_100 = prodModel.countProduct50_100(cateId);
        if (count50_100 > 0) {
            List<Product> prod50_100 = prodModel.getProduct50_100(cateId);
            model.addAttribute("SpecialFood50_100", prod50_100);
        } else {
            model.addAttribute("Fail_SpecialFood50_100", true);
        }
        int count100_200 = prodModel.countProduct100_200(cateId);
        if (count100_200 > 0) {
            List<Product> prod100_200 = prodModel.getProduct100_200(cateId);
            model.addAttribute("SpecialFood100_200", prod100_200);
        } else {
            model.addAttribute("Fail_SpecialFood100_200", true);
        }

        int count_biger200 = prodModel.countProductBiger200(cateId);
        if (count_biger200 > 0) {
            List<Product> prodbiger200 = prodModel.getProductBiger200(cateId);
            model.addAttribute("SpecialFoodbiger200", prodbiger200);
        } else {
            model.addAttribute("Fail_SpecialFoodbiger200", true);
        }

        return "Category_SpecialFood";
    }

    // lay gạo
    @RequestMapping("/category_rice")
    public String cateRice(Model model, HttpServletRequest request, RedirectAttributes redirect) {

        request.getSession().setAttribute("cateRiceList", null);
        if (model.asMap().get("success") != null) {
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        }
        return "redirect:/category_rice/page/1";

    }

    @RequestMapping("/category_rice/page/{pageNumber}")
    public String showCateRicePage(HttpServletRequest request,
            @PathVariable int pageNumber, Model model) throws Exception {

        ProductModel prodModel = new ProductModel();
        List<Product> randRice = prodModel.getRandRice(3);
        model.addAttribute("randRice", randRice);

        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cateRiceList");
        int pagesize = 12;

        List<Product> list = (List<Product>) prodModel.getProductRice();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cateRiceList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/category_rice/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("category_rice", pages);

        cateId = 5;

        //loc theo gia 10_30
        int count10_30 = prodModel.countProduct10_30(cateId);
        if (count10_30 > 0) {
            List<Product> prod10_30 = prodModel.getProduct10_30(cateId);
            model.addAttribute("Rice10_30", prod10_30);
        } else {
            model.addAttribute("Fail_Rice10_30", true);
        }

        int count_30_50 = prodModel.countProduct30_50(cateId);
        if (count_30_50 > 0) {
            List<Product> prod30_50 = prodModel.getProduct30_50(cateId);
            model.addAttribute("Rice30_50", prod30_50);
        } else {
            model.addAttribute("Fail_Rice30_50", true);
        }

        int count50_100 = prodModel.countProduct50_100(cateId);
        if (count50_100 > 0) {
            List<Product> prod50_100 = prodModel.getProduct50_100(cateId);
            model.addAttribute("Rice50_100", prod50_100);
        } else {
            model.addAttribute("Fail_Rice50_100", true);
        }
        int count100_200 = prodModel.countProduct100_200(cateId);
        if (count100_200 > 0) {
            List<Product> prod100_200 = prodModel.getProduct100_200(cateId);
            model.addAttribute("Rice100_200", prod100_200);
        } else {
            model.addAttribute("Fail_Rice100_200", true);
        }

        int count_biger200 = prodModel.countProductBiger200(cateId);
        if (count_biger200 > 0) {
            List<Product> prodbiger200 = prodModel.getProductBiger200(cateId);
            model.addAttribute("Ricebiger200", prodbiger200);
        } else {
            model.addAttribute("Fail_Ricebiger200", true);
        }

        return "Category_Rice";
    }

    @RequestMapping("/category_other")
    public String cateOther(Model model, HttpServletRequest request, RedirectAttributes redirect) {

        request.getSession().setAttribute("cateOtherList", null);
        if (model.asMap().get("success") != null) {
            redirect.addFlashAttribute("success", model.asMap().get("success").toString());
        }
        return "redirect:/category_other/page/1";

    }

    // lay other
    @RequestMapping("/category_other/page/{pageNumber}")
    public String showCateOtherPage(HttpServletRequest request,
            @PathVariable int pageNumber, Model model) throws Exception {

        ProductModel prodModel = new ProductModel();
        List<Product> randOther = prodModel.getRandOther(3);
        model.addAttribute("randOther", randOther);

        PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("cateOtherList");
        int pagesize = 12;

        List<Product> list = (List<Product>) prodModel.getProductOther();
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("cateOtherList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/category_other/page/";
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("category_other", pages);

        cateId = 6;

        //loc theo gia 10_30
        int count10_30 = prodModel.countProduct10_30(cateId);
        if (count10_30 > 0) {
            List<Product> prod10_30 = prodModel.getProduct10_30(cateId);
            model.addAttribute("Other10_30", prod10_30);
        } else {
            model.addAttribute("Fail_Other10_30", true);
        }

        int count_30_50 = prodModel.countProduct30_50(cateId);
        if (count_30_50 > 0) {
            List<Product> prod30_50 = prodModel.getProduct30_50(cateId);
            model.addAttribute("Other30_50", prod30_50);
        } else {
            model.addAttribute("Fail_Other30_50", true);
        }

        int count50_100 = prodModel.countProduct50_100(cateId);
        if (count50_100 > 0) {
            List<Product> prod50_100 = prodModel.getProduct50_100(cateId);
            model.addAttribute("Other50_100", prod50_100);
        } else {
            model.addAttribute("Fail_Other50_100", true);
        }
        int count100_200 = prodModel.countProduct100_200(cateId);
        if (count100_200 > 0) {
            List<Product> prod100_200 = prodModel.getProduct100_200(cateId);
            model.addAttribute("Other100_200", prod100_200);
        } else {
            model.addAttribute("Fail_Other100_200", true);
        }

        int count_biger200 = prodModel.countProductBiger200(cateId);
        if (count_biger200 > 0) {
            List<Product> prodbiger200 = prodModel.getProductBiger200(cateId);
            model.addAttribute("Otherbiger200", prodbiger200);
        } else {
            model.addAttribute("Fail_Otherbiger200", true);
        }

        return "Category_Other";
    }

}

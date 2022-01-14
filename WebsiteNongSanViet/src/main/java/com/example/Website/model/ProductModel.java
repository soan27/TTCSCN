/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Website.model;

import com.example.Website.db.DBConnector;
import com.example.Website.dto.Category;
import com.example.Website.dto.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Hoang Xoan
 */
public class ProductModel {

    private Connection conn;

    public ProductModel() throws Exception {
        this.conn = DBConnector.getConnection();
    }

    public List<Product> getProduct() throws Exception {
        List<Product> products = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDesc(rs.getString("desc"));
            p.setBrief_desc(rs.getString("brief_desc"));
            p.setCateId(rs.getInt("cateId"));
            p.setReal_price(rs.getInt("real_price"));
            p.setPercent_discount(rs.getInt("percent_discount"));
            p.setQuantityProd(rs.getInt("quantityProd"));
            p.setIsHot(rs.getBoolean("isHot"));
            p.setImage(rs.getString("image"));

            products.add(p);
        }
        return products;
    }

    public List<Category> getCategory() throws Exception {
        List<Category> category = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIES ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Category c = new Category();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setDesc(rs.getString("desc"));
            c.setImage(rs.getString("image"));

            category.add(c);

        }
        return category;
    }

//     public Product getCategoryById(int id) throws Exception {
//        
//        String sql = "SELECT name FROM CATEGORIES WHERE id IN (SELECT cateId FROM PRODUCTS WHERE id = ?)";
//        
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1, id);
//         ResultSet rs = pstmt.executeQuery();
//        while (rs.next()) {
//            Product  product = new Product();
//            product.setId(rs.getInt("id"));
//            Category c = new Category();
//            product.setName(rs.getString("name"));
//            product.setDesc(rs.getString("desc"));
//            product.setImage(rs.getString("image"));
//
//            category.add(c);
//
//        }
//        return category;
//    }
    public List<Product> findById(int id) throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));
            products.add(product);

        }
        return products;
    }

    public List<Product> findByCateId(int cateId) throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, cateId);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));
            products.add(product);

        }
        return products;
    }

    public List<Product> getHotProduct(int limit) throws Exception {
        List<Product> products = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE isHot = 1 LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            products.add(product);
        }
        return products;
    }

    public List<Product> getRandProduct(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;
    }

    public List<Product> getProductDiscount(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE percent_discount = 10) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;
    }

    public List<Product> getProductFruit() throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = 1 ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1, limit);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }


    public List<Product> getRandFruit(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1 AND cateId = 1) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;
    }

    public List<Product> getProduct10_30(int cateId) throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 0 AND 30000";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, cateId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }

    public int countProduct10_30(int cateId) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 10000 AND 30000";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, cateId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public List<Product> getProduct30_50(int cateId) throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 30000 AND 50000";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, cateId);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }
    
    public int countProduct30_50(int cateId) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 30000 AND 50000";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, cateId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public List<Product> getProduct50_100(int cateId) throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 50000 AND 100000";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, cateId);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }
    
      public int countProduct50_100(int cateId) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 50000 AND 100000";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, cateId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public List<Product> getProduct100_200(int cateId) throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 100000 AND 200000";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, cateId);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }
    
       public int countProduct100_200(int cateId) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100)BETWEEN 100000 AND 200000";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, cateId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public List<Product> getProductBiger200(int cateId) throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100) > 200000";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, cateId);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }

    public int countProductBiger200(int cateId) throws Exception {
        int total = 0;
        String query = "SELECT COUNT(*) AS TOTAL FROM PRODUCTS WHERE cateId = ? AND (real_price * (100- percent_discount)/100) > 200000";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, cateId);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            total = rs.getInt("total");
        }
        return total;
    }

    public int countFruit() throws Exception {
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT COUNT(*) as TOTAL FROM PRODUCTS WHERE cateId = 1";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        }
        return 0;
    }

    public List<Product> getProductVegetable() throws Exception {
        List<Product> productFruit = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = 2 ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1, limit);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productFruit.add(product);
        }
        return productFruit;
    }

    public List<Product> getRandVegetable(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1 AND cateId = 2) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;
    }

    public List<Product> getProductNut() throws Exception {
        List<Product> productNut = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = 3";
        PreparedStatement pstmt = conn.prepareStatement(sql);
//        pstmt.setInt(1, limit);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productNut.add(product);
        }
        return productNut;
    }

    public List<Product> getRandNut(int limit) throws Exception {
        List<Product> randNut = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1 AND cateId = 3) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randNut.add(product);
        }
        return randNut;
    }

    //lay dac san vung mien
    public List<Product> getProductSpFood() throws Exception {
        List<Product> productSpFood = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = 4";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productSpFood.add(product);
        }
        return productSpFood;
    }

    public List<Product> getRandSpFood(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1 AND cateId = 4) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;

    }

    //lay gạo
    public List<Product> getProductRice() throws Exception {
        List<Product> productRice = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = 5";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productRice.add(product);
        }
        return productRice;
    }

    public List<Product> getRandRice(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1 AND cateId = 5) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;
    }

    //lay gạo
    public List<Product> getProductOther() throws Exception {
        List<Product> productOther = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "SELECT * FROM PRODUCTS WHERE cateId = 6";
        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            productOther.add(product);
        }
        return productOther;
    }

    public List<Product> getRandOther(int limit) throws Exception {
        List<Product> randproducts = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String sql = "(SELECT * FROM PRODUCTS WHERE isHot = 1 AND cateId = 6) ORDER BY RAND() LIMIT ? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, limit);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setDesc(rs.getString("desc"));
            product.setBrief_desc(rs.getString("brief_desc"));
            product.setCateId(rs.getInt("cateId"));
            product.setReal_price(rs.getInt("real_price"));
            product.setPercent_discount(rs.getInt("percent_discount"));
            product.setQuantityProd(rs.getInt("quantityProd"));
            product.setIsHot(rs.getBoolean("isHot"));
            product.setImage(rs.getString("image"));

            randproducts.add(product);
        }
        return randproducts;
    }
    
        public int countSearch(String txtSearch) throws Exception {
        
            try {
                String query = "SELECT COUNT(*) FROM PRODUCTS WHERE `name` LIKE ?";
                Connection conn = DBConnector.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, "%" +txtSearch +"%");
                ResultSet rs = pstmt.executeQuery();
                while(rs.next()){
                    return rs.getInt(1);
                }
            } catch (Exception e) {
            }
        return 0;
    }

  

}

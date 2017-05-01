package com.oreshkevich.webshop.controller;

import com.oreshkevich.webshop.dao.impl.BrandDao;
import com.oreshkevich.webshop.model.Brand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Optional<Brand>> brands = BrandDao.getInstance().getAll();
        String result = "BRANDS: \n";
        for (Optional<Brand> brand : brands) {
            result += "<h1>" + brand.toString() + "</h1>\n";
        }
        resp.getWriter().write(result);
    }
}

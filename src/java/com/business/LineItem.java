/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.business;

import java.text.NumberFormat;

/**
 *
 * @author Viet
 */
public class LineItem {

    private Menu menu;
    private int soluong;

    public LineItem() {
    }

    public void setMenu(Menu m) {
        menu = m;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setQuantity(int soluong) {
        this.soluong = soluong;
    }

    public int getQuantity() {
        return soluong;
    }

    public double getTotal() {
        double total = Double.valueOf(menu.getPrice()) * soluong;
        return total;
    }

    public String getTotalCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(this.getTotal());
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fastfoodstore.dao;

import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface DAOInterface<T> {
    public int insert(T t);
    
    public int update(T t);
    
    public int delete(T t);
    
    public ArrayList<T> selectAll();
    
    public T selectById(String id);
    
    public  ArrayList<T> selectByCondition(String condition1, String condition2);
}



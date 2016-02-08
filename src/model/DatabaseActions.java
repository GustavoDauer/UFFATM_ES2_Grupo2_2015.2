/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gustavo
 */
public interface DatabaseActions {
    
    public boolean insert(HttpServletRequest request);
    public boolean edit(HttpServletRequest request);
    public boolean delete(HttpServletRequest request);
    public boolean view(HttpServletRequest request);
}

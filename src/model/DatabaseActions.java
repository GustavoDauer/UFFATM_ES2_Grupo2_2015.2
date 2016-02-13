/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author gustavo
 */
public interface DatabaseActions {
    
    public boolean insert();
    public boolean edit();
    public boolean delete();
    public boolean view(HttpServletRequest request);
    public boolean viewAll(HttpServletRequest request);
}

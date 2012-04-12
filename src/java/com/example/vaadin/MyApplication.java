/*
 * MyApplication.java
 *
 * Created on 23 mars 2012, 17:15
 */
package com.example.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Window;
import java.util.ArrayList;

/**
 *
 * @author weizhe.jiao
 * @version
 */
public class MyApplication extends Application {

    @Override
    public void init() {
        setTheme("mytheme");
        Window mainWindow = new Window("MyApplication");
        Label label = new Label("DirectProscpet");
        label.setStyleName("titreDirect");
        mainWindow.addComponent(label);
        LoginForm login = new LoginForm();
        login.addStyleName("loginForm");

        login.addListener(new LoginForm.LoginListener() {

            @SuppressWarnings("empty-statement")
            public void onLogin(LoginEvent event) {
                if (authenticateClient(event.getLoginParameter("username"), event.getLoginParameter("password"))) {
                    System.out.println("login");
                }
            }
        });

        mainWindow.addComponent(login);
        
        setMainWindow(mainWindow);
    }

    private Boolean authenticateClient(String name, String pass) {
        StringBuilder queryBuilder = new StringBuilder(); 
        queryBuilder.append("SELECT  id,mail,nom,pass FROM Prospecteurs  "); 

        queryBuilder.append(" where mail = '");
        queryBuilder.append(name);
        queryBuilder.append("' and pass= '"); 
        queryBuilder.append(pass); 
        queryBuilder.append("'"); 

        ArrayList al=SQL.Arr(queryBuilder.toString());
        if (al.size()>0)
            return true;

        return false;
    }
}

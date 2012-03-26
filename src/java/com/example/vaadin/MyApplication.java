/*
 * MyApplication.java
 *
 * Created on 23 mars 2012, 17:15
 */
 
package com.example.vaadin;           

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.data.*;
/** 
 *
 * @author weizhe.jiao
 * @version 
 */

public class MyApplication extends Application {

    @Override
    public void init() {
	Window mainWindow = new Window("MyApplication");
        //Label label = new Label("Hello Vaadin user");
        Label label = new Label( Integer.toString(SQL.Int("select count(id) from prospecteurs")) );
	mainWindow.addComponent(label);
	setMainWindow(mainWindow);
    }

}

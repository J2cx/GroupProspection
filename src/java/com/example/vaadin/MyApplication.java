/*
 * MyApplication.java
 *
 * Created on 23 mars 2012, 17:15
 */
 
package com.example.vaadin;           

import com.vaadin.Application;
import com.vaadin.ui.*;
import com.vaadin.data.*;
import com.vaadin.data.util.BeanItemContainer;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.vaadin.ui.Table;
import java.util.Iterator;
import java.util.Set;
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
        
        String s=Integer.toString(SQL.Arr("select mail,Pre from prospecteurs").size());
        System.out.println(s);
        Label label3 = new Label(s);


        Table t = new Table();
        ArrayList<HashMap> ah = SQL.Arr("select mail,Pre from prospecteurs");
        BeanItemContainer<HashMap> container = new BeanItemContainer<HashMap>(HashMap.class);
        for (HashMap o : ah) {
            container.addBean(o);
        }
        // Create a Set with the keys in the HashMap.
        Set set = ah.get(1).keySet();

        // Iterate over the Set to see what it contains.
        Iterator iter = set.iterator();
        while (iter.hasNext())
        {
            Object o = iter.next();
            System.out.println("keySet: " + o.toString());
            
        }
        Object[] array = set.toArray(); 
        
        t.setContainerDataSource(container);
        t.setVisibleColumns(array);
        
        
        mainWindow.addComponent(label3);
        mainWindow.addComponent(t);
        
	//mainWindow.addComponent(label);
	setMainWindow(mainWindow);
    }



}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author user
 */
import java.beans.*;

public class ButtonTextEditor extends PropertyEditorSupport {
    /** Return the list of value names for the enumerated type. */
    @Override
    public String[] getTags() {
	return new String[] { "button", "BuTtOn", "Large button" };
    }
    
    /** Convert each of those value names into the actual value. */
    @Override
    public void setAsText(String s) { 
        if (s.equals("button")) setValue(ButtonText.button);
	else if (s.equals("BuTtOn")) setValue(ButtonText.BuTtOn);
	else if (s.equals("Large button")) setValue(ButtonText.largeButton);
        else throw new IllegalArgumentException(s);
    }
    
    /** This is an important method for code generation. */
    @Override
    public String getJavaInitializationString() {
	Object o = getValue();
        if (o.equals("button")) return "button";
	else if (o.equals("BuTtOn")) return "BuTtOn";
	else if (o.equals("Large button")) return "Large button";
	return null;
    }
}

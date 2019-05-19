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

public class ToggleTextEditor extends PropertyEditorSupport {
    /** Return the list of value names for the enumerated type. */
    @Override
    public String[] getTags() {
	return new String[] { "toggle", "ToGgLe", "Large toggle" };
    }
    
    /** Convert each of those value names into the actual value. */
    @Override
    public void setAsText(String s) { 
        if (s.equals("toggle")) setValue(ToggleText.toggle);
	else if (s.equals("ToGgLe")) setValue(ToggleText.ToGgLe);
	else if (s.equals("Large toggle")) setValue(ToggleText.largeToggle);
        else throw new IllegalArgumentException(s);
    }
    
    /** This is an important method for code generation. */
    @Override
    public String getJavaInitializationString() {
	Object o = getValue();
        if (o.equals("toggle")) return "toggle";
	else if (o.equals("ToGgLe")) return "ToGgLe";
	else if (o.equals("Large toggle")) return "Large toggle";
	return null;
    }
}

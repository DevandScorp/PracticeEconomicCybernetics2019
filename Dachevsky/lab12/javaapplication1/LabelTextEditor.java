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

public class LabelTextEditor extends PropertyEditorSupport {
    /** Return the list of value names for the enumerated type. */
    @Override
    public String[] getTags() {
	return new String[] { "label", "LaBeL", "Large label" };
    }
    
    /** Convert each of those value names into the actual value. */
    @Override
    public void setAsText(String s) { 
        if (s.equals("label")) setValue(LabelText.label);
	else if (s.equals("LaBeL")) setValue(LabelText.LaBeL);
	else if (s.equals("Large label")) setValue(LabelText.largeLabel);
        else throw new IllegalArgumentException(s);
    }
    
    /** This is an important method for code generation. */
    @Override
    public String getJavaInitializationString() {
	Object o = getValue();
        if (o.equals("label")) return "label";
	else if (o.equals("LaBeL")) return "LaBeL";
	else if (o.equals("Large label")) return "Large label";
	return null;
    }
}

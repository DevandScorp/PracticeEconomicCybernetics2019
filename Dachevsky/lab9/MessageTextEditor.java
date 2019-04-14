package bsu.fpmi.educational_practice2019;

import java.beans.*;

public class MessageTextEditor extends PropertyEditorSupport {
     
    @Override
    public String[] getTags() {
	return new String[] { "Привет", "Hello", "Hi" };
    }
    
     
    @Override
    public void setAsText(String s) { 
        setValue(s);
    }

    @Override
    public String getJavaInitializationString() {
	Object o = getValue();
        if (o.equals("Привет")) return "Привет";
	else if (o.equals("Hello")) return "Hello";
	else if (o.equals("Hi")) return "Hi";
	return null;
    }
}

package junit.test;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinitiont {
	private String id;
	
	private String classString;
	private List<PropertyDefinition> propertys = new ArrayList<PropertyDefinition>();
	public BeanDefinitiont(String id, String classString) {
		this.id = id;
		this.classString = classString;
	}
	
	public List<PropertyDefinition> getPropertys() {
		return propertys;
	}

	public void setPropertys(List<PropertyDefinition> propertys) {
		this.propertys = propertys;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassString() {
		return classString;
	}
	public void setClassString(String classString) {
		this.classString = classString;
	}
	
}

package junit.test;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class ItCastClasspathXmlAppliction {
	private List<BeanDefinitiont> beanDefinitiontslist = new ArrayList<BeanDefinitiont>();
	private Map<String,Object> singletons = new HashMap<String, Object>();
	public ItCastClasspathXmlAppliction(String filename){
		this.readXml(filename);
		this.instanceBean();
		this.injectObject();
	}
	/**
	 * 为bean对象的属性注入值
	 */
	private void injectObject() {
		for(BeanDefinitiont beanDefinitiont:beanDefinitiontslist){
			Object bean =singletons.get(beanDefinitiont.getId());
			if(bean!=null){
				try {
					PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
					for(PropertyDefinition propertyDefinition:beanDefinitiont.getPropertys()){
						for(PropertyDescriptor propertyDesc:ps){
							if(propertyDefinition.getName().equals(propertyDesc.getName())){
								Method setter = propertyDesc.getWriteMethod();//获取属性的set方法 private 
								Object value = singletons.get(propertyDefinition.getRef());
								setter.setAccessible(true);//容器访问为true
								setter.invoke(bean, value);//把引用对象注入到属性
							}
							break;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	/**
	 * 完成bean的实例化
	 */
	private void instanceBean() {
		for( BeanDefinitiont beanDefinitiont :beanDefinitiontslist){
			try {
				if(beanDefinitiont.getClassString()!=null&&!"".equals(beanDefinitiont.getClassString().trim()))
				singletons.put(beanDefinitiont.getId(), Class.forName(beanDefinitiont.getClassString()).newInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	
	/**
	 * 读取配置文件
	 * @param filename
	 */
	private void readXml(String filename) {
		// TODO Auto-generated method stub
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			URL xmlPath = this.getClass().getClassLoader().getResource(filename);
			document = saxReader.read(xmlPath);
			Map<String, String> nsMap = new HashMap<String, String>();
			nsMap.put("ns", "http://www.springframework.org/schema/beans");//加入命名空间
			XPath xsnbPath = document.createXPath("//ns:beans/ns:bean");//创建benas/bean查询路径
			xsnbPath.setNamespaceURIs(nsMap);//设置命名空间
			List<Element> beanElements = xsnbPath.selectNodes(document);//获取文档下的所有bean节点
			for (Element element :beanElements) {
				String id = element.attributeValue("id");
				String clasString = element.attributeValue("class");
				BeanDefinitiont benBeanDefinitiont= new BeanDefinitiont(id ,clasString);
				XPath  propertyPath = element.createXPath("ns:property");
				propertyPath.setNamespaceURIs(nsMap);//设置命名空间
				List<Element> propertys = propertyPath.selectNodes(element);
				for(Element property :propertys){
					String propertyName = property.attributeValue("name");
					String propertyRef = property.attributeValue("ref");
					System.out.println(propertyName+"=" +propertyRef);
					PropertyDefinition propertyDefinition = new PropertyDefinition(propertyName, propertyRef);
					benBeanDefinitiont.getPropertys().add(propertyDefinition);
				}
				beanDefinitiontslist.add(benBeanDefinitiont);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 获取bean实例
	 * @param filename
	 * @return
	 */
	public Object getBean(String filename){
		return this.singletons.get(filename);
	}
}

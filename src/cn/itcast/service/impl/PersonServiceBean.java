package cn.itcast.service.impl;


import cn.itcast.dao.PersonDao;
import cn.itcast.service.PersonService;

public class PersonServiceBean implements PersonService {
	private PersonDao personDao;
	
	public PersonDao getPersonDao() {
		return personDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	private String name;
	private Integer id;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see cn.itcast.service.impl.PersonService#save()
	 */
	public void init(){
		System.out.println("初始化");
	}
	public PersonServiceBean(){
		System.out.println("我被实例化了");
	}
	public void destroy(){
		System.out.println("销毁");
	}
	@Override
	public void save(){
		System.out.println(id+name);
		personDao.add();
	}
}

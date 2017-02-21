import java.util.Collection;

import com.computerDatabase.Pages;
import com.computerDatabase.DAO.CompanyDao;
import com.computerDatabase.DAO.ComputerDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Computer compNew = new Computer("hello2", null, null, 4);
		//compNew.setId(575);
		/*DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDao comp = daoFactory.getCompanyDao();
		
		Collection<Company> comp1 = comp.list();
		
		for(Company com : comp1){
			System.out.println(com.getId());
			System.out.println(com.getName());
		}*/
		//Collection<Computer> comp1 = comp.list();
		Pages page = new Pages();
		page.menu();
		
	}

}

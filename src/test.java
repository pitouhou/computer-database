import java.util.Collection;

import com.computerDatabase.DAO.ComputerDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Computer;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		Collection<Computer> comp1 = comp.list();
		
		for(Computer computer : comp1){
			System.out.println(computer.getId());
			System.out.println(computer.getName());
		}
	}

}

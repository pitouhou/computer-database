import java.util.Collection;

import com.computerDatabase.DAO.ComputerDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Computer;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Computer compNew = new Computer("hello2", null, null, 4);
		//compNew.setId(575);
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		comp.delete(575);
		//Collection<Computer> comp1 = comp.list();
		
		
	}

}

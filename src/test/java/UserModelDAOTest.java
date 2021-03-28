import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTableDAOTest {

    @Test
    void removeAll() {
        UserDAO.deleteAll();
        assertTrue(UserDAO.selectAll().isEmpty());
    }

    @Test
    void insert() {
        UserModel user1 = new UserModel("Bob", 20, false);
        UserModel user2 = new UserModel("Steve", 22, true);
        UserModel user3 = new UserModel("Mike", 19, true);
        UserModel user4 = new UserModel("Harry", 18, true);
        UserModel user5 = new UserModel("John", 25, false);

        UserDAO.insert(user1);
        UserDAO.insert(user2);
        UserDAO.insert(user3);
        UserDAO.insert(user4);
        UserDAO.insert(user5);
    }

    @Test
    void selectAll() {
        assertFalse(UserDAO.selectAll().isEmpty());
    }
}
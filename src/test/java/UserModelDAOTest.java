import com.anderson.dao.UserDAO;
import com.anderson.model.UserModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTableDAOTest {
    @Test
    void insert() {
        UserModel user1 = new UserModel("Bob", 20, false);
        UserDAO.insert(user1);
    }

    @Test
    void remove() {
        UserModel user1 = new UserModel("Steve", 22, true);
        UserDAO.insert(user1);
        assertTrue(UserDAO.wasDeletedById(user1.getId()));
    }

    @Test
    void selectAll() {
        assertFalse(UserDAO.selectAll().isEmpty());
    }
}

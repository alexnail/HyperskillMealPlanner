import java.util.*;

class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User [id " + id + ", name : " + name + "]";
    }
}

interface UserDao {

    void add(User user);

    User get(int id);

    void update(User user);

    void delete(int id);
}

class UserDaoImpl implements UserDao {
    // declare data structure
    private List<User> users;

    public UserDaoImpl() {
        // write your code here
        users = new ArrayList<>();
    }

    @Override
    public void add(User user) {
        // write your code here
        users.add(user);
    }

    @Override
    public User get(int id) {
        // write your code here
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(User user) {
        // write your code here
        users.remove(user);
        users.add(user);
    }

    @Override
    public void delete(int id) {
        // write your code here
        users.removeIf(user -> user.getId() == id);
    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        UserDao userDao = new UserDaoImpl();

        User user1 = new User(scanner.nextInt(), scanner.next());
        User user2 = new User(scanner.nextInt(), scanner.next());
        int inexistentId = scanner.nextInt();

        userDao.add(user1);
        userDao.add(user2);

        // get first
        System.out.println("Found " + userDao.get(user1.getId()));

        // get inexistent user
        if (userDao.get(inexistentId) == null) {
            System.out.println("Not found id " + inexistentId);
        }

        // update and get
        User updateUser = userDao.get(user2.getId());
        System.out.println("Found " + updateUser);
        updateUser.setName("UPDATED");
        userDao.update(updateUser);
        System.out.println("Updated " + userDao.get(user2.getId()));

        // delete
        userDao.delete(user2.getId());
        if (userDao.get(user2.getId()) == null) {
            System.out.println("Deleted id: " + user2.getId());
        }
    }
}
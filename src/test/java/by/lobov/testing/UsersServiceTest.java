package by.lobov.testing;

import com.lobov.HW33.testing.CustomFieldException;
import com.lobov.HW33.testing.Users;
import com.lobov.HW33.testing.UsersService;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UsersServiceTest {

    private static final String EMPTY_NAME = null;
    private static final String FULL_NAME = "name";
    private static final LocalDate FULL_DATE = LocalDate.of(1992, 10, 10);
    private static final LocalDate EMPTY_DATE = null;
    private static final LocalDate BIRTHDAY = LocalDate.of(1992, 10, 10);
    private static final Users EMPTY_USER = null;
    private static final Users USER = new Users(FULL_NAME, FULL_DATE);
    private static final List<Users> USERS_LIST = new ArrayList<>(Arrays.asList(USER));
    private static final UsersService USERS_SERVICE = new UsersService(USERS_LIST);
    private static final String DEFAULT_ERROR_MESSAGE_WITH_EMPTY_NAME = "Name could not be empty or null";
    private static final String DEFAULT_ERROR_MESSAGE_WITH_EMPTY_DATE = "Date of birth could not be null";
    private static final String DEFAULT_ERROR_MESSAGE_WITH_EMPTY_USER_IN_IS_BIRTHDAY_METHOD = "User or date of birth is null";
    private static final String DEFAULT_ERROR_MESSAGE_WITH_EMPTY_DATE_IN_IS_BIRTHDAY_METHOD = "Compare date must not be null";


    @BeforeAll
    static void startTest() {
        System.out.println("This block of tests started");
    }

    @BeforeEach
    void init() {
        System.out.println("Do before each test!");
    }

    @Test
    void testGetUsers() {
        List<Users> testUsers = USERS_SERVICE.getUsers();
        //List<Users> testUsers = Arrays.asList(new Users("name", LocalDate.now()));
        Assertions.assertEquals(testUsers, USERS_LIST);
    }

    @Test
    void testCreateNewUser() throws Exception {
        UsersService usersServiceInTestCreateNewUserMethod = new UsersService(new ArrayList<>());
        List<Users> listForMethodTest = usersServiceInTestCreateNewUserMethod.createNewUser(FULL_NAME, FULL_DATE);
        List<Users> newListForTest = Arrays.asList(new Users(FULL_NAME, FULL_DATE));
        Assertions.assertEquals(newListForTest.toString(), listForMethodTest.toString());
    }

    @Test
    void testCreateNewUserWithNameIsNull() {
        Assertions.assertThrows(CustomFieldException.class, () -> {
            USERS_SERVICE.createNewUser(EMPTY_NAME, FULL_DATE);
        });
    }

    @Test
    void testCreateNewUserWithDateIsNull() {
        Assertions.assertThrows(CustomFieldException.class, () -> {
            USERS_SERVICE.createNewUser(FULL_NAME, EMPTY_DATE);
        });
    }

    @Test
    void testMessageErrorInCreateNewUserWithDateIsNull() {
        Exception exception = Assertions.assertThrows(CustomFieldException.class, () -> {
            USERS_SERVICE.createNewUser(FULL_NAME, EMPTY_DATE);
        });
        Assertions.assertEquals(DEFAULT_ERROR_MESSAGE_WITH_EMPTY_DATE, exception.getMessage());
    }

    @Test
    void testMessageErrorInCreateNewUserWithNameIsNull() {
        Exception exception = Assertions.assertThrows(CustomFieldException.class, () -> {
            USERS_SERVICE.createNewUser(EMPTY_NAME, FULL_DATE);
        });
        Assertions.assertEquals(DEFAULT_ERROR_MESSAGE_WITH_EMPTY_NAME, exception.getMessage());
    }

    @Test
    void testRemoveUser() {
        UsersService usersService = new UsersService(USERS_LIST);
//        usersService.removeUser(FULL_NAME);
        usersService.removeUser("sasha");
        Assertions.assertEquals(USERS_SERVICE.toString(), usersService.toString());
    }


    @Test
    void testIsBirthDay() throws CustomFieldException {
        UsersService usersServiceInIsBirthdayMethod = new UsersService(Arrays.asList(USER));
        //boolean testIsBirthdayMethod = usersServiceInIsBirthdayMethod.isBirthDay(USER, LocalDate.now());
        boolean testIsBirthdayMethod = usersServiceInIsBirthdayMethod.isBirthDay(USER, BIRTHDAY);
        Assertions.assertEquals(testIsBirthdayMethod, true);
    }

    @Test
    void testIsBirthDayWithUserIsNull() {
        Assertions.assertThrows(CustomFieldException.class, () -> {
            UsersService usersService = new UsersService(Arrays.asList(EMPTY_USER));
            usersService.isBirthDay(EMPTY_USER, FULL_DATE);
        });
    }

    @Test
    void testIsBirthDayWithUserDateIsNull() {
        Assertions.assertThrows(CustomFieldException.class, () -> {
            UsersService usersService = new UsersService(Arrays.asList(new Users(FULL_NAME, EMPTY_DATE)));
            usersService.isBirthDay(new Users(FULL_NAME, EMPTY_DATE), FULL_DATE);
        });
    }

    @Test
    void testIsBirthDayWithDateIsNull() {
        Assertions.assertThrows(CustomFieldException.class, () -> {
            UsersService usersService = new UsersService(Arrays.asList(new Users(FULL_NAME, FULL_DATE)));
            usersService.isBirthDay(new Users(FULL_NAME, FULL_DATE), EMPTY_DATE);
        });
    }

    @Test
    void testMessageErrorIsBirthDayWithUserIsNull() {
        Exception exception = Assertions.assertThrows(CustomFieldException.class, () -> {
            UsersService usersService = new UsersService(Arrays.asList(EMPTY_USER));
            usersService.isBirthDay(EMPTY_USER, FULL_DATE);
        });
        Assertions.assertEquals(DEFAULT_ERROR_MESSAGE_WITH_EMPTY_USER_IN_IS_BIRTHDAY_METHOD, exception.getMessage());
    }

    @Test
    void testMessageErrorIsBirthDayWithUserDateIsNull() {
        Exception exception = Assertions.assertThrows(CustomFieldException.class, () -> {
            UsersService usersService = new UsersService(Arrays.asList(new Users(FULL_NAME, EMPTY_DATE)));
            usersService.isBirthDay(new Users(FULL_NAME, EMPTY_DATE), FULL_DATE);
        });
        Assertions.assertEquals(DEFAULT_ERROR_MESSAGE_WITH_EMPTY_USER_IN_IS_BIRTHDAY_METHOD, exception.getMessage());
    }

    @Test
    void testMessageErrorIsBirthDayWithDateIsNull() {
        Exception exception = Assertions.assertThrows(CustomFieldException.class, () -> {
            UsersService usersService = new UsersService(Arrays.asList(new Users(FULL_NAME, FULL_DATE)));
            usersService.isBirthDay(new Users(FULL_NAME, FULL_DATE), EMPTY_DATE);
        });
        Assertions.assertEquals(DEFAULT_ERROR_MESSAGE_WITH_EMPTY_DATE_IN_IS_BIRTHDAY_METHOD, exception.getMessage());
    }

    @AfterEach
    void beforeEachMethod() {
        System.out.println("Method end");
    }

    @AfterAll
    static void beforeAll() {
        System.out.println("All i finished");
    }
}
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateUserTest {

    private UserClient userClient;
    private User user;


    @Before
    public void setUp() {
        userClient = new UserClient();
        user = User.getRandom();
    }

    @After
    public void tearDown() {
        userClient.delete();
    }


    @Test
    @Story("Создание юзера")
    @DisplayName("Создание юзера с одинаковыми данными")
    @Description("Нельзя создать юзера с однинаковыми данными")
    public void userCanNotBeEqual() {
        /*// Arrange
        User user = User.getRandom();*/


        // Act
        userClient.create(user);
        ValidatableResponse statusCodeNegativeResponse = userClient.create(user);


        // Assert
        assertEquals("User already exists", 403, statusCodeNegativeResponse.extract().statusCode());

    }

}

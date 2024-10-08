import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetTests {
    private SoftAssert softAssert;

    @BeforeMethod
    public void setup(){
        softAssert = new SoftAssert();
    }

    @Test(priority = 1)
    public void getBoard() {
        var response = Board.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Board.getBoardName());
        softAssert.assertEquals(response.path("id"),Board.getBoardID());
    }

    @Test(priority = 3)
    public void getList() {
        var response = List.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),List.getListName());
        softAssert.assertEquals(response.path("id"),List.getListID());
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
    }

    @Test(priority = 2)
    public void getLabel() {
        var response = Label.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("name"),Label.getLabelName());
        softAssert.assertEquals(response.path("id"),Label.getLabelID());
        softAssert.assertEquals(response.path("color"),Label.getLabelColor());
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
    }

    @Test(priority = 4)
    public void getCard() {
        var response = Card.get().then().log().all().statusCode(200).extract().response();
        softAssert.assertEquals(response.path("idBoard"),Board.getBoardID());
        softAssert.assertEquals(response.path("idList"),List.getListID());
        softAssert.assertEquals(response.path("id"),Card.getCardID());
    }
}

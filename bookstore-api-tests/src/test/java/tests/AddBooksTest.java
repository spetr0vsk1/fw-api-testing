package tests;

import com.fotoware.models.BookDTO;
import com.fotoware.services.GetBookRestService;
import com.fotoware.services.PostBookRestService;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddBooksTest {

    public GetBookRestService getBookRestService;
    public PostBookRestService postBookRestService;

    @BeforeClass
    public void init() {
        getBookRestService = new GetBookRestService();
        postBookRestService = new PostBookRestService();
    }

    @Test
    public void checkAddBookFunctionality() {
        BookDTO book = BookDTO.builder()
                .isbn(123)
                .author("Stefan")
                .title("Api Test Example")
                .review("Testing")
                .build();
        postBookRestService.postNewBook(book);
        assertEquals(getBookRestService.getBookByISBN(123).getStatusCode(), HttpStatus.SC_OK, "The book was not added to the library");
    }

    @Test
    public void checkDuplicationFilter() {
        BookDTO book = BookDTO.builder()
                .isbn(234)
                .author("Stefan")
                .title("Api Test Example")
                .review("Testing")
                .build();
        postBookRestService.postNewBook(book); //make sure the book exists
        assertEquals(postBookRestService.postNewBook(book).getStatusCode(), HttpStatus.SC_CONFLICT, "An error has occurred when trying to add a book with existing ISBN!");

    }

}

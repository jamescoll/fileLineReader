
import org.junit.*;

import play.Application;

import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.*;
import play.test.*;


import static play.test.Helpers.*;
import static org.junit.Assert.*;


public class ApplicationTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }


    @Test
    public void testBeginningLine(){
        Http.RequestBuilder request1 = new Http.RequestBuilder()
                .method(GET)
                .uri("/lines/1");

        Result result = route(request1);

        assertTrue("1st line of text incorrect", contentAsString(result).contains("This is the 100th Etext file presented by Project Gutenberg"));


    }

    @Test
    public void testFinishingLine(){
        Http.RequestBuilder request1 = new Http.RequestBuilder()
                .method(GET)
                .uri("/lines/124453");

        Result result = route(request1);

        assertTrue("Last line of text incorrect", contentAsString(result).contains("End of this Etext of The Complete Works of William Shakespeare"));

    }

    @Test
    public void testForbiddenReceivedTooLargeNumber(){
        Http.RequestBuilder request1 = new Http.RequestBuilder()
                .method(GET)
                .uri("/lines/20000000");

        Result result = route(request1);

        assertTrue("Incorrect status given", result.status()==403);

    }


    @Test
    public void testForbiddenReceivedNegativeNumber(){
        Http.RequestBuilder request1 = new Http.RequestBuilder()
                .method(GET)
                .uri("/lines/-1");

        Result result = route(request1);

        assertTrue("Incorrect status given", result.status()==403);

    }


}

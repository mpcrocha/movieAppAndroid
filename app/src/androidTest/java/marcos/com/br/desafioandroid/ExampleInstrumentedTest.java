package marcos.com.br.desafioandroid;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import marcos.com.br.desafioandroid.movie.Movie;
import marcos.com.br.desafioandroid.movie.MovieGenreTask;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("marcos.com.br.desafioandroid", appContext.getPackageName());
    }

    @Test
    public void getGenres_isCorrect(){
        Map<String, String> mapGenresTest = new HashMap<>();
        mapGenresTest.put("28","Action");
        mapGenresTest.put("12","Adventure");
        mapGenresTest.put("16","Animation");
        mapGenresTest.put("35","Comedy");
        mapGenresTest.put("80","Crime");
        mapGenresTest.put("99","Documentary");
        mapGenresTest.put("18","Drama");
        mapGenresTest.put("10751","Family");
        mapGenresTest.put("14","Fantasy");
        mapGenresTest.put("36","History");
        mapGenresTest.put("27","Horror");
        mapGenresTest.put("10402","Music");
        mapGenresTest.put("9648","Mystery");
        mapGenresTest.put("10749","Romance");
        mapGenresTest.put("878","Science Fiction");
        mapGenresTest.put("10770","TV Movie");
        mapGenresTest.put("53","Thriller");
        mapGenresTest.put("10752","War");
        mapGenresTest.put("37","Western");

        MovieGenreTask movieGenreTask = new MovieGenreTask();
        Map<String, String> mapGenres = movieGenreTask.getGenresMap();
        assertEquals(mapGenresTest, mapGenres);

    }

    @Test
    public void getNumberStarMovie_isCorrect(){
        Movie movie = new Movie();
        int numStarsMovieTest = 5;
        int numStarsMovie = movie.getNumberStarMovie("10.0");

        assertEquals(numStarsMovieTest, numStarsMovie);
    }

    @Rule
    public ActivityTestRule<movieListActivity> mActivityRule =
            new ActivityTestRule<>(movieListActivity.class);

    @Test
    public void movieTitle_isCorrect() {
        // Type text and then press the button.
        onView(withId(R.id.movie_list)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.movieName)).check(matches(withText("The Secret Life of Pets")));
    }
}

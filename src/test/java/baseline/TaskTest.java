package baseline;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    @Test
    void getDescription() {
        //Create new task and set "Description" and date to now
        Task t = new Task("Description", LocalDate.now());

        //Assert Equal getDescription and "Description
        assertEquals("Description", t.getDescription());

    }

    @Test
    void setDescription() {
        //Create new task and set "" and date to now
        Task t = new Task("", LocalDate.now());
        //setDescription "Description"
        t.setDescription("Description");

        //Assert Equal getDescription and "Description
        assertEquals("Description", t.getDescription());

    }

    @Test
    void getCompleted() {
        //Create new task and set "" and date to now
        Task t = new Task("", LocalDate.now());

        //Assert Equal getCompleted and false
        assertFalse(t.getCompleted().get());

    }

    @Test
    void setCompleted() {
        //Create new task and set "" and date to now
        Task t = new Task("", LocalDate.now());
        //setCompleted "true"
        t.setCompleted(true);

        //Assert Equal getCompleted to True
        assertTrue(t.getCompleted().get());
    }

    @Test
    void getDeadlineDate() {
        //Create new task and set "" and date to now
        Task t = new Task("", LocalDate.now());

        //Assert Equal getDeadlineDate and date to now
        assertEquals(t.getDeadlineDate(), LocalDate.now());
    }

    @Test
    void getDeadline() {
        //Create new task and set "" and date to now
        Task t = new Task("", LocalDate.now());

        //Assert Equal getDeadline and date to now toString
        assertEquals(t.getDeadline(), LocalDate.now().toString());
    }


    @Test
    void setDeadlineDate() {
        //Create new task and set "" and null
        Task t = new Task("", null);
        //setDeadlineDate to Date now
        t.setDeadlineDate(LocalDate.now());

        //Assert Equal getDeadline toString and Date now toString
        assertEquals(t.getDeadline(), LocalDate.now().toString());

    }





}
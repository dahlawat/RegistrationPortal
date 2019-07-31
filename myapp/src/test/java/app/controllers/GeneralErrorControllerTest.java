package app.controllers;

import app.controllers.GeneralErrorController;
import app.controllers.StudentController;
import app.persistence.repositories.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GeneralErrorController.class)
public class GeneralErrorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    public static final String ERROR_PATH = "/error";
    public static final String ERROR_MESSAGE = "Something is wrong in the request !!";

    @Test
    public void errorPageSuccess() throws Exception {
        this.mockMvc.perform(get(ERROR_PATH)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(ERROR_MESSAGE)));
    }
}

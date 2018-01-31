package ru.job4j.servlets.application.methods;

import org.junit.Before;
import org.mockito.Mock;
import ru.job4j.servlets.application.UserStorage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 31.01.2018.
 * @version $Id$.
 * @since 0.1.
 */
public class UsersControllerTest {
    @Mock
    private UserStorage userStorage;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() {
        when(userStorage.isAdmin(anyString())).thenReturn(true);
    }
//TODO
//    @Test
//    public void doGet() throws ServletException, IOException {
//        new UsersController().doGet(request, response);
//    }
}
package ru.job4j.tracker.start;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.job4j.tracker.connection.ConnectionSQL;
import ru.job4j.tracker.models.Item;

import java.sql.*;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 16.12.2017.
 * @version $Id$.
 * @since 0.1.
 */
public class TrackerTestMockito {
    @Mock
    private Tracker tracker;
    @Mock
    private Connection connection;
    @Mock
    private Statement mocStatment;
    @Mock
    private PreparedStatement mocPreparedStatement;
    @Mock
    private ResultSet mocResultSet;
    @Mock
    private ConnectionSQL connectionSQL;
    @Mock
    private Item item;


    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connectionSQL.getConnection()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(mocStatment);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(mocPreparedStatement);
        when(connection.prepareStatement(anyString())).thenReturn(mocPreparedStatement);

        when(mocStatment.executeUpdate(any())).thenReturn(1);
        when(mocStatment.executeQuery(any())).thenReturn(mocResultSet);

        when(mocPreparedStatement.getGeneratedKeys()).thenReturn(mocResultSet);
        doNothing().when(mocPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mocPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mocPreparedStatement).setTimestamp(anyInt(), any());

        when(mocResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        when(mocResultSet.getInt(anyInt())).thenReturn(1);
        when(mocResultSet.getTimestamp(anyString())).thenReturn(new Timestamp(System.currentTimeMillis()));
        when(item.getName()).thenReturn("name");
        when(item.getDesc()).thenReturn("test");
        when(item.getId()).thenReturn("1");
        when(item.getCreated()).thenReturn(123L);

    }

    /**
     * Тест последовательности вызовов создания трэкера.
     * @throws SQLException ех.
     */
    @Test
    public void whenNewTrackerCreatedThenTestInit() throws SQLException {
        new Tracker(connectionSQL);
        verify(connectionSQL, times(1)).getConnection();
        verify(connection, times(1)).createStatement();
        verify(mocStatment, times(1)).executeUpdate(anyString());
    }

    /**
     *Тест проверяет колличество вызовов при добавлении новой заявки.
     * @throws Exception ех.
     */
    @Test
    public void whenAddedNewItem() throws Exception {
        new Tracker(connectionSQL).add(item);
        verify(connection, times(1)).prepareStatement(anyString(), anyInt());
        verify(mocPreparedStatement, times(2)).setString(anyInt(), anyString());
        verify(mocPreparedStatement, times(1)).setTimestamp(anyInt(), any());
        verify(mocPreparedStatement, times(1)).executeUpdate();
        verify(mocPreparedStatement, times(1)).getGeneratedKeys();
        verify(mocResultSet, times(2)).next();
        verify(mocResultSet, times(1)).getInt(1);
        verify(item, times(1)).setId(any());
    }

    /**
     * Тест последовательности вызовов методов при обновлении заявки.
     * @throws Exception ех.
     */
    @Test
    public void whenItemWasUpdate() throws Exception {
        new Tracker(connectionSQL).update(item);
        verify(connection, times(1)).prepareStatement(anyString());
        verify(mocPreparedStatement, times(2)).setString(anyInt(), anyString());
        verify(mocPreparedStatement, times(1)).setInt(anyInt(), anyInt());
        verify(mocPreparedStatement, times(1)).executeUpdate();
    }

    /**
     * Тест проверяет последовательность и колличество вызовов при  удалении заявки.
     * @throws Exception ех.
     */
    @Test
    public void whenItemWasDelete() throws Exception {
        new Tracker(connectionSQL).delete(item);
        verify(connection, times(2)).prepareStatement(anyString());
        verify(item, times(2)).getId();
        verify(mocPreparedStatement, times(2)).setInt(anyInt(),anyInt() );
        verify(mocPreparedStatement, times(2)).executeUpdate();
    }

    @Test
    public void whenWasCalledGetAllMethod() throws Exception {
        new Tracker(connectionSQL).getAll();
        verify(connection, times(1)).createStatement();
        verify(mocStatment, times(1)).executeQuery(anyString());
        verify(mocResultSet, times(2)).next();
        verify(mocResultSet, times(3)).getString(anyString());
        verify(mocResultSet, times(1)).getTimestamp(anyString());
    }
}

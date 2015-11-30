/**
 *
 */
package js.mock;

import js.junit.calc.Calculator;
import js.junit.model.Connection;
import js.junit.service.ComputeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.Timeout;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:Alexeychenko.Vladimir@t-systems.ru">Alexeychenko.Vladimir</a>
 */
public class MockitoConnectionTest {

    @Mock
    private Connection mockConnection;

    @Spy
    private Connection spyConnection;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Rule
    public Timeout globalTimeout = new Timeout(5, TimeUnit.SECONDS);

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void mockCreation() {
        Connection connection = Mockito.mock(Connection.class);
    }


    @Test
    public void spyCreation() {
        Connection connection = Mockito.spy(new Connection());
    }

    @Test
    public void testUsingTempFolder() throws IOException {

        File createdFolder = folder.newFolder("newfolder");
        File createdFile = folder.newFile("myfilefile.txt");

        System.out.println(createdFolder.getAbsolutePath());
        System.out.println(createdFile.getAbsolutePath());

        Assert.assertTrue(createdFile.exists());
    }

    @Test
    @Ignore("Test was ignored because it's fail demonstration")
    public void testGlobalTimeout() throws IOException, InterruptedException {

        startCounter();
    }

    @Test(timeout = 2000)
    @Ignore("Test was ignored because it's fail demonstration")
    public void testLocalTimeout() throws IOException, InterruptedException {

        startCounter();
    }

    private void startCounter() throws InterruptedException {
        long counter = 0;
        long frame = 1000;

        while (true) {
            Thread.currentThread().sleep(frame);
            counter += frame;
            System.out.println("time: " + counter);
        }
    }


    @Test
    public void verifyPresentation() {

        mockConnection.closeConnection();
        mockConnection.closeConnection();

        Mockito.verify(mockConnection, Mockito.atLeastOnce()).closeConnection();
        Mockito.verify(mockConnection, Mockito.times(2)).closeConnection();
        Mockito.verify(mockConnection, Mockito.never()).isActive();

        Mockito.reset(mockConnection);

        mockConnection.isActive();
        mockConnection.isActive();
        mockConnection.isActive();

        Mockito.verify(mockConnection, Mockito.never()).closeConnection();
        Mockito.verify(mockConnection, Mockito.atMost(5)).isActive();

    }

    @Test
    public void preconditionsPresentation() {

        Mockito.when(mockConnection.isActive()).thenReturn(true);
        mockConnection.setActive(false);

        Assert.assertEquals(true, mockConnection.isActive());

    }

    @Test(expected = IllegalArgumentException.class)
    public void preconditionsExceptionPresentation() {

        Mockito.when(mockConnection.isActive()).thenThrow(new IllegalArgumentException("Some info"));

        mockConnection.isActive();

    }

    @Test
    public void preconditionsExceptionPresentation_viaRule() {

        String errorMessage = "Some info";
        Mockito.when(mockConnection.isActive()).thenThrow(new IllegalArgumentException(errorMessage));

        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(errorMessage);

        mockConnection.isActive();

    }

    @Test
    public void spyPresentation() {

        spyConnection.setActive(true);
        Assert.assertEquals(true, spyConnection.isActive());

        spyConnection.setActive(false);
        Assert.assertEquals(false, spyConnection.isActive());

        Mockito.doNothing().when(spyConnection).setActive(Mockito.anyBoolean());

        spyConnection.setActive(true);
        Assert.assertEquals(false, spyConnection.isActive());


        Mockito.reset(spyConnection);

        Mockito.when(spyConnection.isActive()).thenReturn(true);

        spyConnection.setActive(false);

        Assert.assertEquals(true, spyConnection.isActive());
        Assert.assertEquals(false, spyConnection.getRealConnectionState());


    }


    @Test
    public void inOrderPresentation() {

        InOrder inOrder = Mockito.inOrder(mockConnection);

        mockConnection.isActive();
        mockConnection.getRealConnectionState();
        mockConnection.isActive();

        inOrder.verify(mockConnection).isActive();
        inOrder.verify(mockConnection).getRealConnectionState();
        inOrder.verify(mockConnection).isActive();

    }

    @Test
    public void servicePresentation() throws Exception {

        Integer wrongValue = 100500;

        Mockito.when(mockConnection.isActive()).thenReturn(true);

        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.maxValue(Mockito.anyInt(), Mockito.anyInt())).thenReturn(wrongValue);

        ComputeService service = new ComputeService(calculator, mockConnection);

        Assert.assertEquals(wrongValue, service.computeMaxValue(1, 2));
    }

}

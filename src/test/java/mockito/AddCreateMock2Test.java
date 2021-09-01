package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AddCreateMock2Test {

    @Mock
    private ValidNumber validNumber;

    @InjectMocks
    private Add add;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addTest() {
        add.add(3,2);
        Mockito.verify(validNumber).check(3);
    }
}

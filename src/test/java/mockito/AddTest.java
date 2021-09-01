package mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class AddTest {

    @Mock
    private ValidNumber validNumber;
    @Mock
    private Print print;
    @Captor
    private ArgumentCaptor<Integer> captor;

    @InjectMocks
    private Add add;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addMockExceptionTest(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No podemos aceptar cero"));
        assertThrows(ArithmeticException.class, () -> validNumber.checkZero(0));
    }

    @Test
    public void addRealMethodTest(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertTrue(validNumber.check(3));

        when(validNumber.check("3")).thenCallRealMethod();
        assertFalse(validNumber.check("3"));
    }

    @Test
    public void addDoubleToIntThenAnswerTest(){
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };

        when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14, add.addInt(8.7));
    }

    @Test
    public void patterTest(){
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4,5);
        //Assert
        assertEquals(9, result);
    }

    @Test
    public void patterBDDTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9, result);
    }

    @Test
    public void argumentMatcherTest(){
        //Given
        given(validNumber.check(anyInt())).willReturn(true);
        //When
        int result = add.add(4,5);
        //Then
        assertEquals(9, result);
    }

    @Test
    public void addPrintTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(validNumber).check(4);
        //verify(validNumber, times(2)).check(4);
        verify(validNumber, never()).check(99);
        verify(validNumber, atLeast(1)).check(4);
        verify(validNumber, atMost(3)).check(4);

        verify(print).showMessage(9);
        verify(print, never()).showError();
    }

    @Test
    public void captorTest(){
        //Given
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //When
        add.addPrint(4,5);
        //Then
        verify(print).showMessage(captor.capture());
        assertEquals(captor.getValue().intValue(), 8);
    }

    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2, spyList.size());

    }

    @Test
    public void mockTest(){
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        given(mockList.size()).willReturn(2);
        assertEquals(2, mockList.size());

    }


}

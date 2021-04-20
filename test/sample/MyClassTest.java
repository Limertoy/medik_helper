package sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {

    @Test
    void dodaj() {
        MyClass myInstance = new MyClass();
        int input_data1 = 1;
        int input_data2 = 2;
        int return_value = myInstance.dodaj(input_data1, input_data2);
        int expected = 3;
        Assertions.assertEquals(return_value, expected);
    }
}
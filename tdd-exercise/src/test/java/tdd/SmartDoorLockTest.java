package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {

    private SmartDoorLock doorLock;
    private int pin;
    private int wrongPin;

    @BeforeEach
    void beforeEach(){
         doorLock = new SmartDoorLockImpl();
         pin = 1234;
         wrongPin = 5678;
    }

    @Test
    void testIsDoorLockBlocked() {
        assertFalse(doorLock.isBlocked());
    }

    @Test
    void testIsDoorLockLocked(){
        assertFalse(doorLock.isLocked());
    }

    @Test
    void testDoorCanBeLocked(){
        doorLock.setPin(pin);
        doorLock.lock();
        assertTrue(doorLock.isLocked());
    }

    @Test
    void testPinCanUnlockDoor(){
        doorLock.setPin(pin);
        doorLock.lock();
        doorLock.unlock(pin);
        assertFalse(doorLock.isLocked());
    }

    @Test
    void testWrongPinCantUnlockTheDoor(){
        doorLock.setPin(pin);
        doorLock.lock();
        doorLock.unlock(wrongPin);
        assertTrue(doorLock.isLocked());
    }

    @Test
    void testToManyWrongPinBlockTheDoor(){
        doorLock.setPin(pin);
        doorLock.lock();
        doorLock.unlock(wrongPin);
        doorLock.unlock(wrongPin);
        doorLock.unlock(wrongPin);
        assertTrue(doorLock.isBlocked());
    }

    @Test
    void testDoorLockReset(){
        testToManyWrongPinBlockTheDoor();
        doorLock.reset();
        testIsDoorLockLocked();
        testIsDoorLockBlocked();
    }


}

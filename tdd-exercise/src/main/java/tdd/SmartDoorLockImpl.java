package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {

    private static final int PIN_MAX_VALUE = 9999;
    private static final int PIN_MIN_VALUE = 1000;
    private static final int MAX_UNLOCKING_ATTEMPTS_BEFORE_BLOCK = 3;
    private static final int RESET_WRONG_ATTEMPT_NUMBER = 0;
    private boolean isPinSetted = false;
    private boolean isLocked = false;
    private boolean isBlocked = false;
    private int pin;
    private int wrongUnlockingAttemptsNumber = 0;

    @Override
    public void setPin(int pin) {
        if(!isLocked && !isBlocked()) {
            if (pin >= PIN_MIN_VALUE && pin <= PIN_MAX_VALUE) {
                this.pin = pin;
                isPinSetted = true;
            }
        }
    }

    @Override
    public void unlock(int pin) {
        if (isLocked && !isBlocked) {
            if (this.pin == pin) {
                this.isLocked = false;
                wrongUnlockingAttemptsNumber = RESET_WRONG_ATTEMPT_NUMBER;
            } else {
                checkFailedAttempts();
            }
        }

    }

    private void checkFailedAttempts() {
        wrongUnlockingAttemptsNumber++;
        if (wrongUnlockingAttemptsNumber == MAX_UNLOCKING_ATTEMPTS_BEFORE_BLOCK) {
            this.isBlocked = true;
        }
    }

    @Override
    public void lock() {
        if(isPinSetted) {
            this.isLocked = true;
        } else {
            throw new IllegalStateException("Pin is not setted");
        }
    }

    @Override
    public boolean isLocked() {
        return isLocked;
    }

    @Override
    public boolean isBlocked() {
        return isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return MAX_UNLOCKING_ATTEMPTS_BEFORE_BLOCK;
    }

    @Override
    public int getFailedAttempts() {
        return wrongUnlockingAttemptsNumber;
    }

    @Override
    public void reset() {

    }
}

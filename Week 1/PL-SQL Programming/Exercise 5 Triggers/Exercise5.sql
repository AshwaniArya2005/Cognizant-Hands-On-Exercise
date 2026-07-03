-- Scenario 1: Auto-update LastModified when customer record changes
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

UPDATE Customers SET Balance = Balance + 100 WHERE CustomerID = 1;
SELECT CustomerID, Name, LastModified FROM Customers WHERE CustomerID = 1;

-- Scenario 2: Audit log for every new transaction
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (LogID, TransactionID, AccountID, Amount, LogDate, Action)
    VALUES (audit_seq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, :NEW.Amount, SYSDATE, 'INSERT');
END;
/

INSERT INTO Transactions VALUES (3, 1, SYSDATE, 500, 'Deposit');
SELECT * FROM AuditLog;

-- Scenario 3: Enforce business rules on Transactions
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Deposit amount must be positive.');
    END IF;
    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = :NEW.AccountID;
        IF v_balance < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20004, 'Withdrawal exceeds account balance.');
        END IF;
    END IF;
END;
/

-- Test Scenario 3: Valid withdrawal (Should succeed)
BEGIN
    INSERT INTO Transactions VALUES (4, 2, SYSDATE, 200, 'Withdrawal');
    DBMS_OUTPUT.PUT_LINE('Valid withdrawal inserted successfully.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Unexpected Error: ' || SQLERRM);
END;
/

-- Test Scenario 3: Invalid deposit (Should be blocked by trigger)
BEGIN
    INSERT INTO Transactions VALUES (5, 2, SYSDATE, -50, 'Deposit');
    DBMS_OUTPUT.PUT_LINE('Warning: Invalid deposit bypassed trigger!');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Trigger blocked invalid deposit: ' || SQLERRM);
END;
/

-- Test Scenario 3: Excessive withdrawal (Should be blocked by trigger)
BEGIN
    INSERT INTO Transactions VALUES (6, 2, SYSDATE, 99999, 'Withdrawal');
    DBMS_OUTPUT.PUT_LINE('Warning: Excessive withdrawal bypassed trigger!');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Trigger blocked excessive withdrawal: ' || SQLERRM);
END;
/


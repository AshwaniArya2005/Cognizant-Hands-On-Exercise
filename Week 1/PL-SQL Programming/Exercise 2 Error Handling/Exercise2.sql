SET SERVEROUTPUT ON;

-- Scenario 1: Safe fund transfer with rollback on error
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_source IN NUMBER,
    p_dest   IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source FOR UPDATE;
    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds in account ' || p_source);
    END IF;
    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_source;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_dest;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of ' || p_amount || ' successful.');
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: Account not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

EXEC SafeTransferFunds(1, 2, 500);
EXEC SafeTransferFunds(2, 1, 99999);

-- Scenario 2: Update employee salary with error handling
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_emp_id  IN NUMBER,
    p_percent IN NUMBER
) AS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_count FROM Employees WHERE EmployeeID = p_emp_id;
    IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee ID ' || p_emp_id || ' does not exist.');
    END IF;
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percent / 100)
    WHERE EmployeeID = p_emp_id;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated for EmployeeID: ' || p_emp_id);
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

EXEC UpdateSalary(1, 10);
EXEC UpdateSalary(99, 10);

-- Scenario 3: Add new customer with duplicate ID handling
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id      IN NUMBER,
    p_name    IN VARCHAR2,
    p_dob     IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('ERROR: Customer ID ' || p_id || ' already exists.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END;
/

EXEC AddNewCustomer(5, 'New User', TO_DATE('1995-01-01','YYYY-MM-DD'), 2000);
EXEC AddNewCustomer(1, 'Duplicate', TO_DATE('1995-01-01','YYYY-MM-DD'), 2000);

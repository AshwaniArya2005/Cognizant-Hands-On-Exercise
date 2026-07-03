-- Scenario 1: Customer management package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_dob IN DATE, p_balance IN NUMBER);
    PROCEDURE UpdateCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_balance IN NUMBER);
    FUNCTION GetBalance(p_id IN NUMBER) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_dob IN DATE, p_balance IN NUMBER) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Customer ID ' || p_id || ' already exists.');
    END;

    PROCEDURE UpdateCustomer(p_id IN NUMBER, p_name IN VARCHAR2, p_balance IN NUMBER) AS
    BEGIN
        UPDATE Customers SET Name = p_name, Balance = p_balance, LastModified = SYSDATE
        WHERE CustomerID = p_id;
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Customer ID ' || p_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Customer updated: ' || p_id);
        END IF;
    END;

    FUNCTION GetBalance(p_id IN NUMBER) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN -1;
    END;
END CustomerManagement;
/

SET SERVEROUTPUT ON;
EXEC CustomerManagement.AddCustomer(10, 'Test User', TO_DATE('1992-06-10','YYYY-MM-DD'), 5000);
EXEC CustomerManagement.UpdateCustomer(10, 'Test User Updated', 7500);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Balance of CustomerID 10: $' || CustomerManagement.GetBalance(10));
END;
/

-- Scenario 2: Employee management package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(p_id IN NUMBER, p_name IN VARCHAR2, p_pos IN VARCHAR2, p_salary IN NUMBER, p_dept IN VARCHAR2);
    PROCEDURE UpdateEmployee(p_id IN NUMBER, p_pos IN VARCHAR2, p_salary IN NUMBER);
    FUNCTION GetAnnualSalary(p_id IN NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee(p_id IN NUMBER, p_name IN VARCHAR2, p_pos IN VARCHAR2, p_salary IN NUMBER, p_dept IN VARCHAR2) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_pos, p_salary, p_dept, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee hired: ' || p_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Employee ID ' || p_id || ' already exists.');
    END;

    PROCEDURE UpdateEmployee(p_id IN NUMBER, p_pos IN VARCHAR2, p_salary IN NUMBER) AS
    BEGIN
        UPDATE Employees SET Position = p_pos, Salary = p_salary WHERE EmployeeID = p_id;
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Employee ID ' || p_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Employee updated: ' || p_id);
        END IF;
    END;

    FUNCTION GetAnnualSalary(p_id IN NUMBER) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN -1;
    END;
END EmployeeManagement;
/

EXEC EmployeeManagement.HireEmployee(20, 'Dave Green', 'Tester', 50000, 'QA');
EXEC EmployeeManagement.UpdateEmployee(20, 'Senior Tester', 58000);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Annual salary of EmpID 20: $' || EmployeeManagement.GetAnnualSalary(20));
END;
/

-- Scenario 3: Account operations package
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id IN NUMBER, p_cust_id IN NUMBER, p_type IN VARCHAR2, p_balance IN NUMBER);
    PROCEDURE CloseAccount(p_acc_id IN NUMBER);
    FUNCTION GetTotalBalance(p_cust_id IN NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount(p_acc_id IN NUMBER, p_cust_id IN NUMBER, p_type IN VARCHAR2, p_balance IN NUMBER) AS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_acc_id, p_cust_id, p_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ' || p_acc_id || ' opened for CustomerID ' || p_cust_id);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Account ID ' || p_acc_id || ' already exists.');
    END;

    PROCEDURE CloseAccount(p_acc_id IN NUMBER) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_acc_id;
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('ERROR: Account ID ' || p_acc_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Account ' || p_acc_id || ' closed.');
        END IF;
    END;

    FUNCTION GetTotalBalance(p_cust_id IN NUMBER) RETURN NUMBER AS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total FROM Accounts WHERE CustomerID = p_cust_id;
        RETURN v_total;
    END;
END AccountOperations;
/

EXEC AccountOperations.OpenAccount(30, 1, 'Savings', 3000);
BEGIN
    DBMS_OUTPUT.PUT_LINE('Total balance for CustomerID 1: $' || AccountOperations.GetTotalBalance(1));
END;
/
EXEC AccountOperations.CloseAccount(30);

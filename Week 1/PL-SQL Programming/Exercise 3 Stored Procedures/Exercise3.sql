SET SERVEROUTPUT ON;

-- Scenario 1: Apply 1% monthly interest to all Savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest applied to all Savings accounts.');
END;
/

EXEC ProcessMonthlyInterest;
SELECT * FROM Accounts;

-- Scenario 2: Add bonus to employees by department
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department   IN VARCHAR2,
    p_bonus_pct    IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_bonus_pct / 100)
    WHERE Department = p_department;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus of ' || p_bonus_pct || '% applied to ' || p_department);
END;
/

EXEC UpdateEmployeeBonus('IT', 10);
SELECT * FROM Employees;

-- Scenario 3: Transfer funds between accounts
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_source IN NUMBER,
    p_dest   IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_source;
    IF v_balance >= p_amount THEN
        UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_source;
        UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_dest;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer successful: ' || p_amount || ' from Acc ' || p_source || ' to Acc ' || p_dest);
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient balance in account ' || p_source);
    END IF;
END;
/

EXEC TransferFunds(1, 2, 1000);
EXEC TransferFunds(4, 1, 99999);
SELECT * FROM Accounts;

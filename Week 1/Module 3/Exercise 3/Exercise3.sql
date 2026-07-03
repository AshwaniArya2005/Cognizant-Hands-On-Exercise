CREATE TABLE Accounts (
    AccountID   NUMBER PRIMARY KEY,
    CustomerID  NUMBER,
    AccountType VARCHAR2(20),
    Balance     NUMBER
);

INSERT INTO Accounts VALUES (101, 1, 'Savings', 10000);
INSERT INTO Accounts VALUES (102, 2, 'Savings',  5000);
INSERT INTO Accounts VALUES (103, 3, 'Current',  8000);

COMMIT;

CREATE TABLE Employees (
    EmployeeID   NUMBER PRIMARY KEY,
    EmployeeName VARCHAR2(50),
    Department   VARCHAR2(30),
    Salary       NUMBER(10, 2)
);

INSERT INTO Employees VALUES (1, 'Rahul', 'IT',      50000);
INSERT INTO Employees VALUES (2, 'Priya', 'HR',      45000);
INSERT INTO Employees VALUES (3, 'Rohan', 'IT',      60000);
INSERT INTO Employees VALUES (4, 'Anita', 'Finance', 55000);

COMMIT;

SELECT * FROM Accounts;
SELECT * FROM Employees;

-- Scenario 1: Apply 1% monthly interest to all Savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
AS
BEGIN
    UPDATE Accounts
    SET Balance = Balance + (Balance * 0.01)
    WHERE AccountType = 'Savings';

    COMMIT;
END;
/

EXEC ProcessMonthlyInterest;

SELECT * FROM Accounts;

-- Scenario 2: Update employee bonus by department and percentage
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    dept         IN VARCHAR2,
    bonusPercent IN NUMBER
)
AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * bonusPercent / 100)
    WHERE Department = dept;

    COMMIT;
END;
/

-- Scenario 3: Transfer funds between accounts
EXEC UpdateEmployeeBonus('IT', 10);

SELECT * FROM Employees;

CREATE OR REPLACE PROCEDURE TransferFunds(
    sourceAcc IN NUMBER,
    destAcc   IN NUMBER,
    amount    IN NUMBER
)
AS
    balance NUMBER;
BEGIN
    SELECT Balance
    INTO balance
    FROM Accounts
    WHERE AccountID = sourceAcc;

    IF balance >= amount THEN
        UPDATE Accounts
        SET Balance = Balance - amount
        WHERE AccountID = sourceAcc;

        UPDATE Accounts
        SET Balance = Balance + amount
        WHERE AccountID = destAcc;

        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Transfer Successful');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient Balance');
    END IF;
END;
/

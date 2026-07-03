CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    CustomerName VARCHAR2(50),
    Age          NUMBER,
    Balance      NUMBER,
    IsVIP        VARCHAR2(5)
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    InterestRate NUMBER,
    DueDate      DATE,
    CONSTRAINT fk_customer FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID)
);

INSERT INTO Customers VALUES (1, 'Rahul', 65, 15000, 'FALSE');
INSERT INTO Customers VALUES (2, 'Priya', 45,  8000, 'FALSE');
INSERT INTO Customers VALUES (3, 'Rohan', 70, 12000, 'FALSE');
INSERT INTO Customers VALUES (4, 'Anita', 30,  5000, 'FALSE');

INSERT INTO Loans VALUES (101, 1, 10, SYSDATE + 20);
INSERT INTO Loans VALUES (102, 2,  8, SYSDATE + 50);
INSERT INTO Loans VALUES (103, 3,  9, SYSDATE + 10);
INSERT INTO Loans VALUES (104, 4, 11, SYSDATE + 100);

COMMIT;

-- Scenario 1: Reduce interest rate by 1 for customers older than 60
SET SERVEROUTPUT ON;
DECLARE
BEGIN
    FOR c IN (SELECT CustomerID, Age FROM Customers)
    LOOP
        IF c.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = c.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest rates updated.');
END;
/

SELECT * FROM Loans;

-- Scenario 2: Mark customers with balance > 10000 as VIP
SET SERVEROUTPUT ON;
BEGIN
    FOR c IN (SELECT CustomerID, Balance FROM Customers)
    LOOP
        IF c.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = c.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('VIP status updated.');
END;
/

SELECT * FROM Customers;

-- Scenario 3: Send reminders for loans due within the next 30 days
SET SERVEROUTPUT ON;
BEGIN
    FOR l IN (
        SELECT c.CustomerName, l.DueDate
        FROM Customers c
        JOIN Loans l ON c.CustomerID = l.CustomerID
        WHERE l.DueDate <= SYSDATE + 30
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: '
            || l.CustomerName
            || ' has a loan due on '
            || TO_CHAR(l.DueDate, 'DD-MON-YYYY')
        );
    END LOOP;
END;
/

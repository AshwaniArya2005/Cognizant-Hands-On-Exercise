CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    DOB          DATE,
    Balance      NUMBER,
    LastModified DATE,
    IsVIP        VARCHAR2(5) DEFAULT 'FALSE'
);

CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    AccountType  VARCHAR2(20),
    Balance      NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID   NUMBER PRIMARY KEY,
    AccountID       NUMBER,
    TransactionDate DATE,
    Amount          NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    LoanAmount   NUMBER,
    InterestRate NUMBER,
    StartDate    DATE,
    EndDate      DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name       VARCHAR2(100),
    Position   VARCHAR2(50),
    Salary     NUMBER,
    Department VARCHAR2(50),
    HireDate   DATE
);

CREATE TABLE AuditLog (
    LogID         NUMBER PRIMARY KEY,
    TransactionID NUMBER,
    AccountID     NUMBER,
    Amount        NUMBER,
    LogDate       DATE,
    Action        VARCHAR2(50)
);

CREATE SEQUENCE audit_seq START WITH 1 INCREMENT BY 1;

INSERT INTO Customers VALUES (1, 'John Doe',    TO_DATE('1950-05-15','YYYY-MM-DD'), 15000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (2, 'Jane Smith',  TO_DATE('1990-07-20','YYYY-MM-DD'),  8000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (3, 'Bob Rajan',   TO_DATE('1955-03-10','YYYY-MM-DD'), 12000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (4, 'Alice Nair',  TO_DATE('2000-01-01','YYYY-MM-DD'),  5000, SYSDATE, 'FALSE');

INSERT INTO Accounts VALUES (1, 1, 'Savings',  15000, SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking',  8000, SYSDATE);
INSERT INTO Accounts VALUES (3, 3, 'Savings',  12000, SYSDATE);
INSERT INTO Accounts VALUES (4, 4, 'Savings',   5000, SYSDATE);

INSERT INTO Transactions VALUES (1, 1, SYSDATE,  200, 'Deposit');
INSERT INTO Transactions VALUES (2, 2, SYSDATE,  300, 'Withdrawal');

INSERT INTO Loans VALUES (1, 1, 5000,  10, SYSDATE, SYSDATE + 20);
INSERT INTO Loans VALUES (2, 2, 8000,   8, SYSDATE, SYSDATE + 50);
INSERT INTO Loans VALUES (3, 3, 6000,   9, SYSDATE, SYSDATE + 10);
INSERT INTO Loans VALUES (4, 4, 3000,  11, SYSDATE, SYSDATE + 100);

INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager',   70000, 'HR', TO_DATE('2015-06-15','YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown',     'Developer', 60000, 'IT', TO_DATE('2017-03-20','YYYY-MM-DD'));
INSERT INTO Employees VALUES (3, 'Carol White',   'Analyst',   55000, 'IT', TO_DATE('2019-09-01','YYYY-MM-DD'));

COMMIT;

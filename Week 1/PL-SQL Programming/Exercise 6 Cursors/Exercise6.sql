SET SERVEROUTPUT ON;

-- Scenario 1: Monthly statements for all customers
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, t.TransactionID, t.Amount, t.TransactionType, t.TransactionDate
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR  FROM t.TransactionDate) = EXTRACT(YEAR  FROM SYSDATE);
BEGIN
    FOR rec IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE(
            rec.Name || ' | TxnID: ' || rec.TransactionID ||
            ' | ' || rec.TransactionType || ' | $' || rec.Amount ||
            ' | ' || TO_CHAR(rec.TransactionDate, 'DD-MON-YYYY')
        );
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Monthly statements generated.');
END;
/

-- Scenario 2: Deduct annual maintenance fee from all accounts
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    v_fee NUMBER := 50;
BEGIN
    FOR rec IN ApplyAnnualFee LOOP
        IF rec.Balance >= v_fee THEN
            UPDATE Accounts SET Balance = Balance - v_fee WHERE AccountID = rec.AccountID;
            DBMS_OUTPUT.PUT_LINE('Fee deducted from AccountID: ' || rec.AccountID);
        ELSE
            DBMS_OUTPUT.PUT_LINE('Insufficient balance for fee in AccountID: ' || rec.AccountID);
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee processing complete.');
END;
/

-- Scenario 3: Update loan interest rates based on new policy
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
    v_new_rate NUMBER;
BEGIN
    FOR rec IN UpdateLoanInterestRates LOOP
        IF rec.InterestRate > 9 THEN
            v_new_rate := rec.InterestRate - 0.5;
        ELSE
            v_new_rate := rec.InterestRate + 0.25;
        END IF;
        UPDATE Loans SET InterestRate = v_new_rate WHERE LoanID = rec.LoanID;
        DBMS_OUTPUT.PUT_LINE('LoanID: ' || rec.LoanID || ' rate: ' || rec.InterestRate || ' -> ' || v_new_rate);
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rates updated.');
END;
/

SET SERVEROUTPUT ON;

-- Scenario 1: Apply 1% discount to loan interest for customers older than 60
DECLARE
    v_age NUMBER;
BEGIN
    FOR c IN (SELECT CustomerID, DOB FROM Customers) LOOP
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12);
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = c.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for CustomerID: ' || c.CustomerID);
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 1 complete.');
END;
/

-- Scenario 2: Promote customers with balance > 10000 to VIP
BEGIN
    FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF c.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = c.CustomerID;
            DBMS_OUTPUT.PUT_LINE('VIP set for CustomerID: ' || c.CustomerID);
        END IF;
    END LOOP;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 2 complete.');
END;
/

-- Scenario 3: Send reminders for loans due within the next 30 days
BEGIN
    FOR l IN (
        SELECT c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate <= SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Reminder: ' || l.Name ||
            ' - Loan due on ' || TO_CHAR(l.EndDate, 'DD-MON-YYYY')
        );
    END LOOP;
    DBMS_OUTPUT.PUT_LINE('Scenario 3 complete.');
END;
/

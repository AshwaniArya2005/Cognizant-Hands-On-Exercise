SET SERVEROUTPUT ON;

-- Scenario 1: Calculate customer age from DOB
CREATE OR REPLACE FUNCTION CalculateAge(p_dob IN DATE)
RETURN NUMBER AS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END;
/

BEGIN
    FOR c IN (SELECT Name, DOB FROM Customers) LOOP
        DBMS_OUTPUT.PUT_LINE(c.Name || ' - Age: ' || CalculateAge(c.DOB));
    END LOOP;
END;
/

-- Scenario 2: Calculate monthly loan installment (EMI formula)
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount  IN NUMBER,
    p_interest_rate IN NUMBER,
    p_years        IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate NUMBER;
    v_months       NUMBER;
    v_emi          NUMBER;
BEGIN
    v_monthly_rate := (p_interest_rate / 100) / 12;
    v_months := p_years * 12;
    IF v_monthly_rate = 0 THEN
        RETURN ROUND(p_loan_amount / v_months, 2);
    END IF;
    v_emi := p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)
             / (POWER(1 + v_monthly_rate, v_months) - 1);
    RETURN ROUND(v_emi, 2);
END;
/

BEGIN
    DBMS_OUTPUT.PUT_LINE('EMI for $5000, 5%, 2 years: $' || CalculateMonthlyInstallment(5000, 5, 2));
    DBMS_OUTPUT.PUT_LINE('EMI for $10000, 8%, 5 years: $' || CalculateMonthlyInstallment(10000, 8, 5));
END;
/

-- Scenario 3: Check if account has sufficient balance
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    RETURN v_balance >= p_amount;
EXCEPTION
    WHEN NO_DATA_FOUND THEN RETURN FALSE;
END;
/

BEGIN
    IF HasSufficientBalance(1, 500) THEN
        DBMS_OUTPUT.PUT_LINE('Account 1: Sufficient balance for $500.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1: Insufficient balance for $500.');
    END IF;

    IF HasSufficientBalance(4, 99999) THEN
        DBMS_OUTPUT.PUT_LINE('Account 4: Sufficient balance for $99999.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 4: Insufficient balance for $99999.');
    END IF;
END;
/

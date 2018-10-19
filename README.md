Money Allocating App
====================
Application allocates given amount of money to earlier specified funds according to chosen investing style.
Whole module consists of 

Java Classes:
- Fund.class 
- FundType.class (abstract)
- PolishType.class
- ForeignType.class
- MonetaryType.class
- FundsPacket.class
- FundInvestor.class
- Investment.class
- InvestingStyle.class

Java Interface:
- Investor

Description
--------------------
Fund.class is parametrized by FundType.class and defines single fund instance. FundType.class is an abstract class which serves as a base for other PolishType.class, #ForeignType.class, #MonetaryType.class subclasses.Few funds might be kept in FundsPacket instance. FundInvestor.class implements Investor.class interface which contains invest() methods. To perform money allocation invocation of invest() method by FundInvestor.class instance 
must be performed. Invest() method takes two parameters - some part of money to invest and InvestingStyle.class instance which defines how to dispose money per fund. In result invest() creates and return Investment.class instance which is also added to investmentList - one of the Investor's fields. To prevent allocating fraction part of money to funds, investmentCheck() method form Investment is applied.

Tests
--------------------
Test of cases described in task description can be found in InvestorTestFirstAndSecond.class and InvestorTestThirdVariant.class.
Launching InvestorTest.class causes launching suite of rest of the tests.

still TODO
--------------------
The whole app would require to be refactored due to using double types instead of BigDecimal.
Coverage of the test could be better. Exception/Sad path are margin
SpringBoot could be applied.



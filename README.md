Money Allocating App
====================
Application allocates given amount of money to earlier specified funds according to chosen investing style.
Whole module consists of 

Java Classes:
- Fund.class
- FundsPacket.class
- Investment.class
- Investor.class

Java Interface:
- IInvestable

Java Enums:
- FundType
- InvestingStyleDef

'Fund' class defines single fund instance. Few funds might be kept in 'FundsPacket' instance
To perform money allocation invocation of 'invest' method from 'Investor' class by #Investor instance must be called. It creates 'Investment' instance which is added to 'invesmentList' - one of the #Investor's fields.
Interface 'IInvestable' contains 'invest' method which is implemented in 'Investor'. To prevent allocating fraction part of money to funds, 'investmentCheck' method on 'Investment' is applied.

Tests
--------------------
Test of cases described in task description can be found in 'InvestorTest.class' and 'InvestorTestThirdVariant.class'. These tests cover 90% of lines of code.
'FundTest.class' examines remained methods and in union with test classes above cover 96% of code. 
'AllTests.class' can be run to launch all tests at once.




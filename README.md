# ATM
Author: Colin St. Claire
Email: colinst.claire@gmail.com
Project Description: This is a simple terminal mimickry of an iQ Credit Union ATM written in Java for my Fundamentals of Software Engineering course.

To compile:
$ cd [directory containing source files]
$ javac ATM.java

To run:
$ java ATM

Note, because there is no Database to work with, use the account number 12345678, which has been hard-coded into the Bank's Account data structure. Upon Bank object creation, 30 8-digit account numbers are randomly added, all with an initial balance of $100.00.

Files:
README.txt ------------- This file
ATM.java --------------- Contains the main() function and the ATM class.
Account.java ----------- Contains the Account class
Transaction.java ------- Contains the Transaction class
Bank.java -------------- Contains the Bank class
TransNode.java --------- Contains the TransNode class, which is just for storing transaction information for each Account object. For displaying balance and account transaction history.

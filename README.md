Algo:-

A list of sell orders, buy orders are maintained
in the order of their arrival.


For every buy order we iterate the sell order list from the 
start and check if the price matching conditions are met.
In case the conditions are update check met we remove matched sell order 
from orderBook and move to the ledger. 
All the cases with updation of quantity are handled.


Compile - gradle clean build

Run - java -jar build/libs/geektrust.jar input.txt
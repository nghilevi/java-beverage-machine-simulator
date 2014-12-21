This is the answer for the technical question. The program prints out an activity log of the coffee machine in a defined period of time. It also prints out the execution time of the simulator. (Make sure to download the latest version to prevent legacy bugs and get the most up-to-date README.md)

##How to run the program:
You can download this repository and open the project in Eclipse. Go to src -> answer -> Main.java, then click the Run button. Type in inputs in the console window and press Enter. 

Sample output:

![Screenshot](https://raw.githubusercontent.com/vinhnghi223/ZI2014-Nghi/master/Screenshot.PNG "Screenshot")

##Explanation

The output prints out 2 seperate queues set next to each other, seperated by "|" for ease of comparison.

* NORMAL QUEUE represents a natural queue of people regardless of their busy status. The one who enters the queue first (as can be seen in Enter-Queue-At column) stays on top.

Take an example in this screenshot, you can read from left to right as follows: "From 9:00 to 10:00, Engineer 7 who started becoming super busy at 9:41 and became non super busy at 11:11. He entered queue at 9:07, so at the time he entered the queue he was Non super busy" The same logic applies for other engineers in the same hour.

* PRIORITIZED QUEUE represents a queue of people processed by the coffee machine, meaning when an engineer is super busy he is prioritized before non super busy ones. In other words, PRIORITIZED QUEUE is a NORMAL QUEUE which has been sorted based on the busy status and, optionally, based on whether or not the time of making a cup of coffee is taken into account (For this reason, the super busy one is not always the one who stays on top as this also depends on the time he joint the queue). Therefore, for the convinient of checking, the program prints out 3 columns representing the engineer id, his busy status when on queue, and the time he entered the queue. 

When start the simulator, it's up to you to choose whether or not "the time to make a cup of coffee" is taken into consideration:

Use this when the time of making a cup of coffee is NOT taken into account:
```sh
coffeeMachineSimulator=new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION);
```

Or use this in the reversed case:
```sh
coffeeMachineSimulator=new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION,MAKE_ONE_COFFEE_TIME);
```
In this case, it is assumed that the time to make a cup of coffee is 2.5 minutes. This number can also be adjusted easily in the Constants.java file depending on the user.

To understand more about these 2 queues, please read more about the explanation on Status-On-Queue.
 
* Engineer-Id: each engineer-id represents an engineer.

* Start-Super-Busy-At: The specific time an engineer becomes super busy. This is generated randomly based on the chance that the engineer can become super-busy in a unit of time.

* End-Super-Busy-At: The specific time an engineer becoms non super busy. This is calculated by started busy time + time stay super busy (in minutes) inputted by the user.

* Status-On-Queue: the busy status when the engineer is standing in the queue (Super-Busy or Non-Super-Busy). This is decided based on whether the time the engineer enter the queue is before or after the period he/she has become super busy and, optionally, the time of making a cup of coffee.

For example, let's check the case of engineer 56 as below. Even though he entered the queue (13:12) before he started super busy (13:14), he is still considered as super busy because the time to make a cup of coffee, in this case, is 2.5 minutes, is taken into account. 

![Screenshot](https://raw.githubusercontent.com/vinhnghi223/ZI2014-Nghi/master/Screenshot2.PNG "This is not a bug")

In  PRIORITIZED QUEUE, engineer 56 was priopritized before engineer 52 even though engineer 52 entered the queue before engineer 56. The reason is the gap between the entered time of these 2 engineers was 2 minutes, less than the time of making a cup of coffee (2.5 minutes, in this case). So the one who is super busy will be priopritized before the non super busy one. If the time of making a cup of coffee is not taken into account, then no matter when the non super busy entered the queue, he will always be arranged below the super busy ones.

##Other notes about this implementation:
* For representational purpose, the program uses a default start working time, and working duration to define the boudary of what to be printed out. This can be modified easily in the Constants.java file.

* It is assumed that in each hour, all engineers drink one cup of coffee whenever he/she likes.

* The program uses ArrayList as the data structure to store the queue of engineers object. This is due to its efficiency of random access operations which have been used intensively in the program.

* As not defined in the technical question and for the constraint time of implementation, currently the program has not been implemented to handle certain situations such as where the user give unrealistic inputs.

* More comments/notes/explanations are also written inside the program.

Thanks for reading! :)

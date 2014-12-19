This is the answer for the technical question. The program prints out an activity log of the coffee machine in a defined period of time. It also prints out the execution time of the program. 

##How to run the program:
You can download this project and open it in Eclipse. Go to src -> answer -> Main.java, then click the Run button.
Type in inputs and press Enter. 

<Screenshot>
NORMAL QUEUE represents a natural queue of people regardless of their busy status. The one who enters the queue first, stays on top.

PRIORITIZED QUEUE represents a queue of people processed by the coffee machine, meaning when an engineer is super-busy he is prioritized before non-super-busy ones. This queue will be sorted differently depending on whether or not the time of making a cup of coffee is taken into account. (The program provides you options to to turn this "time of making a cup of coffee" on or off)

The output prints out 2 seperate queues for ease of comparison.


##Some notes about this implementation:
_ For representational purpose, the program uses a default start working time, and working duration to define the boudary of what to be printed out. This can be modified easily.
_ It is assumed that in each hour, all engineers drink one cup of coffee whenever he/she likes.
_ When start the simulator, it's up to you to choose whether or not "the time to make a cup of coffee" is taken into consideration:

Use this when the time of making a cup of coffee is NOT taken into consideration:
```sh
coffeeMachineSimulator=new CoffeeMachineSimulator(START_WORKING_TIME,WORKING_DURATION);
```

Or use this in the reverse case:
```sh
coffeeMachineSimulator=new CoffeeMachineSimulatorSTART_WORKING_TIME,WORKING_DURATION,MAKE_ONE_COFFEE_TIME);
```
It is assumed that the time to make a cup of coffee is 2.5 minutes. This can also be adjusted easily.

Some other comments/notes/explanation is written inside the program.

Thanks for reading! :)

**RentAll**<br>
**Local communities sharing platform**<br>
Link to application RentAll [Frontend Angular](https://github.com/MK-Sad/RentAllAngular)

RentAll is an application designed for local communities, it helps neighbors to share and borrow things like household items, tools or toys.
You can search and scroll items shared by your neighbors.
If you want to borrow an item, click on “Rent [item] for [x] days” and await for an owner decision.
The owner will get an email and accept or deny your request.
Each user collects points, which provide some information about a particular user and are visible for the item's owner in the email message.
![alt text](https://github.com/MK-Sad/rentAll/blob/master/src/main/resources/static/images/Panel%20view.jpg?raw=true)

**Architecture**

Application structure has been based on 3 modules with a single responsibility: item, rental (main logic is placed here) and user.
Each module has a REST controller, facade, repository and a separate database table. 
There is a low coupling between modules.
The only 2 relations occur between rental and item module (rental sends information and item’s status is changed) and between rental and user module (initialization of sending email to the owner and calculating points by using events).
![alt text](https://github.com/MK-Sad/rentAll/blob/master/Backend.jpg)

High modularity gives a possibility of moving easily to microservices architecture and making the app more scalable.
![alt text](https://github.com/MK-Sad/rentAll/blob/master/src/main/resources/static/images/Microservices.jpg?raw=true)
